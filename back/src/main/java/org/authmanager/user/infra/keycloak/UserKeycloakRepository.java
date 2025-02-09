package org.authmanager.user.infra.keycloak;

import java.util.Optional;

import org.authmanager.user.domain.models.User;
import org.authmanager.user.domain.ports.out.UserRepository;

public class UserKeycloakRepository implements UserRepository{

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }
    
}
