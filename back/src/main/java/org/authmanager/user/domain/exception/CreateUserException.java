package org.authmanager.user.domain.exception;

import org.authmanager.lib.CustomError;

public class CreateUserException extends CustomError {

    public CreateUserException() {
        super("Create User Exception");
    }

    public CreateUserException(String message) {
        super(message);
    }

}