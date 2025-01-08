package org.authmanager.infrastructure.database.repository;

import java.util.Optional;

import org.authmanager.domain.entity.User;
import org.authmanager.domain.ports.UserDomainRepository;
import org.authmanager.infrastructure.database.entity.UserEntity;

public class UserRepository implements UserDomainRepository {
    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity.persist();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity userEntity = UserEntity.find("email", email).firstResult();

        if (userEntity == null) {
            return Optional.empty();
        }

        return Optional.of(userEntity.toDomain());
    }
}
