package org.authmanager.config;

import org.authmanager.domain.ports.out.Authentification;
import org.authmanager.infra.keycloak.KeycloakAuth;
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

    @Produces
    public Authentification authentification() {
        return new KeycloakAuth(serverUrl, realm, clientId, clientSecret);
    }
}
