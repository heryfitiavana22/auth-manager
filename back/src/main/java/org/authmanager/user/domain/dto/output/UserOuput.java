package org.authmanager.user.domain.dto.output;

import java.util.ArrayList;
import java.util.List;

public class UserOuput {
    private String name;
    private String email;
    private List<RoleOutput> roles = new ArrayList<>();

    public UserOuput() {
    }

    public UserOuput(String name, String email, String password, List<RoleOutput> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleOutput> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleOutput> roles) {
        this.roles = roles;
    }

    public void addRole(RoleOutput role) {
        this.roles.add(role);
    }

    public void addRole(String name) {
        this.roles.add(new RoleOutput(name));
    }

    public void removeRole(RoleOutput role) {
        this.roles.remove(role);
    }

    public boolean hasRole(RoleOutput role) {
        return this.roles.contains(role);
    }

    @Override
    public String toString() {
        return "UserOuput{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
