package school21.spring.service.models;

import java.util.Objects;

public class User {

    private Long Identifier;
    private String Email;

    public User() {
        Identifier = 0l;
        Email = "";
    }

    public User(String email) {
        Identifier = 0l;
        Email = email;
    }

    public User(Long identifier, String email) {
        Identifier = identifier;
        Email = email;
    }

    public Long getIdentifier() {
        return Identifier;
    }

    public String getEmail() {
        return Email;
    }

    public void setIdentifier(Long identifier) {
        Identifier = identifier;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Identifier.equals(user.Identifier) && Email.equals(user.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Identifier);
    }

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + Identifier +
                ", Email='" + Email + '\'' +
                '}';
    }
}
