package org.authmanager.domain.ports;

import java.util.Optional;

import org.authmanager.domain.dto.input.Login;
import org.authmanager.domain.dto.input.Register;
import org.authmanager.domain.dto.output.Token;

public interface AuthService {
    Token register(Register user);

    Optional<Token> login(Login login);
}
