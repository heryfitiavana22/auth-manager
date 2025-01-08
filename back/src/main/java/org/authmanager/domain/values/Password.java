package org.authmanager.domain.values;

public class Password {
    private String value;

    public Password(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Password does not meet security requirements");
        }
        this.value = value;
    }

    private boolean isValid(String password) {
        return password != null && password.length() >= 8;
    }

    public String getValue() {
        return value;
    }
}
