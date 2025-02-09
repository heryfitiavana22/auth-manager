package org.authmanager.user.app;

import org.authmanager.lib.ErrorType;
import org.authmanager.lib.Result;
import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;
import org.authmanager.user.domain.ports.AuthService;
import org.authmanager.user.domain.ports.out.Authentification;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthUseCase implements AuthService {
    private Authentification authentification;

    public AuthUseCase(Authentification authentification) {
        this.authentification = authentification;
    }

    @Override
    public Token register(Register user) {
        return authentification.register(user);
    }

    @Override
    public Result<Token, ErrorType> login(Login login) {
        return authentification.login(login);
    }

}
