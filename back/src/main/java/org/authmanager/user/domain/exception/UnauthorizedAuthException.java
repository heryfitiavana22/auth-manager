package org.authmanager.user.domain.exception;

import org.authmanager.lib.CustomError;

public class UnauthorizedAuthException extends CustomError {

    public UnauthorizedAuthException() {
        super("Unauthorized");
    }

    public UnauthorizedAuthException(String message) {
        super(message);
    }

}
