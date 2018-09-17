package io.box.boxio.data;

public class User {
    private final String username;
    private final String email;

    public User(final String username, final String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
