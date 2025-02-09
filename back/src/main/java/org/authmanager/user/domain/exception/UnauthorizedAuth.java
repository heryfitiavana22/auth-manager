package org.authmanager.user.domain.exception;

import org.authmanager.lib.ErrorType;

public class UnauthorizedAuth implements ErrorType {
    @Override
    public String message() {
        return "Unauthorized";
    }

}
