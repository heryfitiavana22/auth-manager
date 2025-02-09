package org.authmanager.user.domain.ports.in;

import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;

public interface AuthRest {
    Token register(Register user);

    Token login(Login login);
}
