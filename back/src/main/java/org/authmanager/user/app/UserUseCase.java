package org.authmanager.user.app;

import org.authmanager.user.domain.dto.output.UserOuput;
import org.authmanager.user.domain.exception.UnauthorizedAuthException;
import org.authmanager.user.domain.ports.UserService;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserUseCase implements UserService {

    @Override
    public UserOuput me(String token) throws UnauthorizedAuthException {
        try {
            TokenVerifier<AccessToken> tokenVerifier = TokenVerifier.create(token, AccessToken.class)
                    .parse();

            AccessToken accessToken = tokenVerifier.getToken();

            UserOuput userOuput = new UserOuput();
            userOuput.setEmail(accessToken.getEmail());
            userOuput.setName(accessToken.getPreferredUsername());
            accessToken.getRealmAccess().getRoles().forEach(role -> userOuput.addRole(role));
            return userOuput;
        } catch (VerificationException e) {
            throw new UnauthorizedAuthException("VerificationException " + e.getMessage());
        }
    }

}
