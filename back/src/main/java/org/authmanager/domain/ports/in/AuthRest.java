package org.authmanager.domain.ports.in;

import org.authmanager.domain.dto.input.Login;
import org.authmanager.domain.dto.input.Register;
import org.authmanager.domain.dto.output.Token;

public interface AuthRest {
    Token register(Register user);

    Token login(Login login);
}
