package org.authmanager.infrastructure.database.entity;

import org.authmanager.domain.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntity {
    public String name;
    public String email;
    public String password;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User toDomain() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
}
