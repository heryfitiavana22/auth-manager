package org.authmanager.user.domain.ports.in;

import org.authmanager.user.domain.dto.output.UserOuput;

public interface UserRest {
    UserOuput me(String token);
}
