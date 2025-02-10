package org.authmanager.user.infra.keycloak;

import org.authmanager.lib.CustomError;
import org.authmanager.lib.Result;
import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;
import org.authmanager.user.domain.exception.CreateUserException;
import org.authmanager.user.domain.exception.UnauthorizedAuthException;
import org.authmanager.user.domain.exception.UserExistedException;
import org.authmanager.user.domain.ports.out.Authentification;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.ws.rs.core.Response;

public class KeycloakAuth implements Authentification {
    private String SERVER_URL;
    private String REALM;
    private String CLIENT_ID;
    private String CLIENT_SECRET;
    private String ADMIN_USERNAME;
    private String ADMIN_PASSWORD;

    public KeycloakAuth(String serverUrl, String realm, String clientId, String clientSecret, String adminUsername,
            String adminPassword) {
        this.SERVER_URL = serverUrl;
        this.REALM = realm;
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
        this.ADMIN_USERNAME = adminUsername;
        this.ADMIN_PASSWORD = adminPassword;
    }

    @Override
    public Token register(Register registerUser) throws UserExistedException, CreateUserException {
        Keycloak keycloakSuperadmin = loginKeycloak(new Login(ADMIN_USERNAME, ADMIN_PASSWORD));
        UsersResource usersResource = keycloakSuperadmin.realm(REALM).users();

        if (!usersResource.search(registerUser.getUsername()).isEmpty()) {
            throw new UserExistedException();
        }
        UserRepresentation user = new UserRepresentation();
        user.setUsername(registerUser.getUsername());
        user.setFirstName(registerUser.getUsername());
        user.setLastName(registerUser.getUsername());
        user.setEmail(registerUser.getEmail());
        user.setEnabled(true);

        Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            throw new CreateUserException("Failed to create user " + response.getStatus());
        }

        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(registerUser.getPassword());

        usersResource.get(userId).resetPassword(passwordCred);

        var loginUser = login(new Login(registerUser.getUsername(), registerUser.getPassword()));
        return loginUser.unwrap();
    }

    @Override
    public Result<Token, CustomError> login(Login login) {
        try {
            Keycloak keycloak = loginKeycloak(login);

            AccessTokenResponse tokenResponse = keycloak.tokenManager().grantToken();
            Token token = new Token(tokenResponse.getToken(), tokenResponse.getRefreshToken(),
                    tokenResponse.getExpiresIn(), tokenResponse.getRefreshExpiresIn());

            return Result.ok(token);
        } catch (Exception e) {
            return Result.err(new UnauthorizedAuthException());
        }
    }

    Keycloak loginKeycloak(Login login) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .grantType("password")
                .username(login.getUsername())
                .password(login.getPassword())
                .build();
        return keycloak;
    }

}
