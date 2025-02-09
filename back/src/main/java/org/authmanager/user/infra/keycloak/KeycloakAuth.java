package org.authmanager.user.infra.keycloak;

import org.authmanager.lib.ErrorType;
import org.authmanager.lib.Result;
import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;
import org.authmanager.user.domain.exception.UnauthorizedAuth;
import org.authmanager.user.domain.ports.out.Authentification;
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
    public Result<Token, ErrorType> login(Login login) {
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
            Token token = new Token(tokenResponse.getToken(), tokenResponse.getRefreshToken(),
                    tokenResponse.getExpiresIn(), tokenResponse.getRefreshExpiresIn());

            return Result.ok(token);
        } catch (Exception e) {
            return Result.err(new UnauthorizedAuth());
        }
    }

}
