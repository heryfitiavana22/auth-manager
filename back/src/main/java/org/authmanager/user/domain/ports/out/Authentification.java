package org.authmanager.user.domain.ports.out;

import org.authmanager.lib.ErrorType;
import org.authmanager.lib.Result;
import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;

public interface Authentification {
    Token register(Register user);

    Result<Token, ErrorType> login(Login login);
}
