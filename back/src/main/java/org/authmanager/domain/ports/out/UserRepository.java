package org.authmanager.domain.ports.out;

import java.util.Optional;

import org.authmanager.domain.models.User;

public interface UserRepository {
    void save(User user);

    Optional<User> findByEmail(String email);
}
