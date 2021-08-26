package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long            userId;
    private String          login;
    private String          password;

    private List<Chatroom>  createdRooms;
    private List<Chatroom>  createdMessages;

    public User(Long userId, String login, String password, List<Chatroom> createdRooms, List<Chatroom> createdMessages) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.createdMessages = createdMessages;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getCreatedMessages() {
        return createdMessages;
    }

    public void setCreatedMessages(List<Chatroom> createdMessages) {
        this.createdMessages = createdMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}