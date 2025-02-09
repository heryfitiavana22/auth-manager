package org.authmanager.user.config;

import org.authmanager.user.domain.ports.out.Authentification;
import org.authmanager.user.infra.keycloak.KeycloakAuth;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class AuthConfig {
    @Inject
    @ConfigProperty(name = "keycloak.url")
    String serverUrl;

    @Inject
    @ConfigProperty(name = "keycloak.realm")
    String realm;

    @Inject
    @ConfigProperty(name = "keycloak.client-id")
    String clientId;

    @Inject
    @ConfigProperty(name = "keycloak.client-secret")
    String clientSecret;

    private String ADMIN_USERNAME = "superadmin";
    private String ADMIN_PASSWORD = "admin";

    @Produces
    public Authentification authentification() {
        return new KeycloakAuth(serverUrl, realm, clientId, clientSecret, ADMIN_USERNAME, ADMIN_PASSWORD);
    }
}
