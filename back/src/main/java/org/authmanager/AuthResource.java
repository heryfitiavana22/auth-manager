package org.authmanager;

import org.eclipse.microprofile.config.inject.ConfigProperty;

// @Path("/auth")
public class AuthResource {
        @ConfigProperty(name = "keycloak.url")
        private String SERVER_URL;
        @ConfigProperty(name = "keycloak.realm")
        private String REALM;
        @ConfigProperty(name = "keycloak.client-id")
        private String CLIENT_ID;
        @ConfigProperty(name = "keycloak.client-secret")
        private String CLIENT_SECRET;

        protected String ADMIN_USERNAME = "superadmin";
        protected String ADMIN_PASSWORD = "admin";

}
