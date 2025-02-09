package org.authmanager;


import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;

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

}
