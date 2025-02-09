package org.authmanager.app;

import java.util.Optional;

import org.authmanager.domain.dto.input.Login;
import org.authmanager.domain.dto.input.Register;
import org.authmanager.domain.dto.output.Token;
import org.authmanager.domain.ports.AuthService;
import org.authmanager.domain.ports.out.Authentification;

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
    public Optional<Token> login(Login login) {
        return authentification.login(login);
    }

}
