package org.authmanager.user.domain.ports;

import org.authmanager.user.domain.dto.output.UserOuput;
import org.authmanager.user.domain.exception.UnauthorizedAuthException;

public interface UserService {
    UserOuput me(String token) throws UnauthorizedAuthException;
}
