package org.authmanager.domain.values;

public class Email {
    private String value;

    public Email() {
    }

    public Email(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value;
    }

    private boolean isValid(String email) {
        return email != null && email.contains("@");
    }

    public String getValue() {
        return value;
    }
}
