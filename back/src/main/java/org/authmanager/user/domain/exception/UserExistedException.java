package org.authmanager.user.domain.exception;

import org.authmanager.lib.CustomError;

public class UserExistedException extends CustomError {

    public UserExistedException() {
        super("User existed");
    }

    public UserExistedException(String message) {
        super(message);
    }

}
