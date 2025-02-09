package org.authmanager.user.app;

import org.authmanager.lib.ErrorType;
import org.authmanager.lib.Result;
import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;
import org.authmanager.user.domain.exception.CreateUserException;
import org.authmanager.user.domain.exception.UserExisted;
import org.authmanager.user.domain.ports.AuthService;
import org.authmanager.user.domain.ports.out.Authentification;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthUseCase implements AuthService {
    private Authentification authentification;

    public AuthUseCase(Authentification authentification) {
        this.authentification = authentification;
    }

    @Override
    @RolesAllowed("superadmin")
    public Token register(Register user) throws UserExisted, CreateUserException {
        return authentification.register(user);
    }

    @Override
    public Result<Token, ErrorType> login(Login login) {
        return authentification.login(login);
    }

}
