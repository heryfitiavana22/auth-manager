package org.authmanager.domain.ports;

import java.util.Optional;

import org.authmanager.domain.entity.User;

public interface UserDomainRepository {
    void save(User user);

    Optional<User> findByEmail(String email);
}
