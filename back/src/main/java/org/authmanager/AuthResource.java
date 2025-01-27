package org.authmanager;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;

@Path("/auth")
public class AuthResource {
    @ConfigProperty(name = "keycloak.url")
    private String SERVER_URL;
    @ConfigProperty(name = "keycloak.realm")
    private String REALM;
    @ConfigProperty(name = "keycloak.client-id")
    private String CLIENT_ID;
    @ConfigProperty(name = "keycloak.client-secret")
    private String CLIENT_SECRET;

    @POST
    @Path("/login")
    @PermitAll
    public Response login(Credentials credentials) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .grantType("password")
                    .username(credentials.username)
                    .password(credentials.password)
                    .build();

            AccessTokenResponse tokenResponse = keycloak.tokenManager().grantToken();
            return Response.ok(tokenResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Échec de l'authentification : " + e.getMessage())
                    .build();
        }
    }

}
