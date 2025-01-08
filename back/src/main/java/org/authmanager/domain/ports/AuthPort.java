package org.authmanager.domain.ports;

import org.authmanager.domain.entity.User;

public interface AuthPort {
    void registerUser(User user);

    User loginUser(String email, String password);
}
