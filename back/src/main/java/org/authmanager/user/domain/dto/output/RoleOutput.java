package org.authmanager.user.domain.dto.output;

public class RoleOutput {
    private String name;

    public RoleOutput() {
    }

    public RoleOutput(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
