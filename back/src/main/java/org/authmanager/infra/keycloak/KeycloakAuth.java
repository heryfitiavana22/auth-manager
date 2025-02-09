package org.authmanager.infra.keycloak;

import java.util.Optional;

import org.authmanager.domain.dto.input.Login;
import org.authmanager.domain.dto.input.Register;
import org.authmanager.domain.dto.output.Token;
import org.authmanager.domain.ports.out.Authentification;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

public class KeycloakAuth implements Authentification {
    private String SERVER_URL;
    private String REALM;
    private String CLIENT_ID;
    private String CLIENT_SECRET;

    public KeycloakAuth(String serverUrl, String realm, String clientId, String clientSecret) {
        this.SERVER_URL = serverUrl;
        this.REALM = realm;
        this.CLIENT_ID = clientId;
        this.CLIENT_SECRET = clientSecret;
    }

    @Override
    public Token register(Register user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Optional<Token> login(Login login) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .grantType("password")
                    .username(login.getUsername())
                    .password(login.getPassword())
                    .build();

            AccessTokenResponse tokenResponse = keycloak.tokenManager().grantToken();

            return Optional.of(
                    new Token(tokenResponse.getToken(), tokenResponse.getRefreshToken(), tokenResponse.getExpiresIn(),
                            tokenResponse.getRefreshExpiresIn()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
