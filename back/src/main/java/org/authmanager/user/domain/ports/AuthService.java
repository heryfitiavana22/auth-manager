package org.authmanager.user.domain.ports;

import org.authmanager.lib.ErrorType;
import org.authmanager.lib.Result;
import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;
import org.authmanager.user.domain.exception.CreateUserException;
import org.authmanager.user.domain.exception.UserExisted;

public interface AuthService {
    Token register(Register user) throws UserExisted, CreateUserException;

    Result<Token, ErrorType> login(Login login);
}
