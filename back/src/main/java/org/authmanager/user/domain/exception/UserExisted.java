package org.authmanager.user.domain.exception;

import org.authmanager.lib.CustomError;

public class UserExisted extends CustomError {

    public UserExisted() {
        super("User existed");
    }

    public UserExisted(String message) {
        super(message);
    }

}
