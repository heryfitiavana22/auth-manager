package org.authmanager.user.domain.ports;

import java.util.Optional;

import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;

public interface AuthService {
    Token register(Register user);

    Optional<Token> login(Login login);
}
