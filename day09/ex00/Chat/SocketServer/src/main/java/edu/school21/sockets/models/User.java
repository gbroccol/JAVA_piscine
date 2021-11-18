package edu.school21.sockets.models;

import java.util.Objects;

public class User {

    private Long identifier;
    private String username;
    private String password;

    public User(Long identifier, String username, String password) {
        this.identifier = identifier;
        this.username = username;
        this.password = password;
    }

    public User() {
        this(0l, "", "");
    }

    public User(String username) {
        this(0l, username, "");
    }

    public User(Long id, String username) {
        this(id, username, "");
    }

    public User(String username, String password) {
        this(0l, username, password);
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identifier.equals(user.identifier) && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + identifier +
                ", Email='" + username + '\'' +
                '}';
    }
}
