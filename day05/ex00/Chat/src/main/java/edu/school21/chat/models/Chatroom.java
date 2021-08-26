package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Long            chatroomId;
    private String          name;
    private Integer         ownerId;

    private List<Message>   messages;
    private User            owner;

    public Chatroom(Long chatroomId, String name, Integer ownerId, List<Message> messages, User owner) {
        this.chatroomId = chatroomId;
        this.name = name;
        this.ownerId = ownerId;
        this.messages = messages;
        this.owner = owner;
    }

    public Long getChatroomId() {
        return chatroomId;
    }

    public String getName() {
        return name;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User getOwner() {
        return owner;
    }

    public void setChatroomId(Long chatroomId) {
        this.chatroomId = chatroomId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatroomId.equals(chatroom.chatroomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatroomId=" + chatroomId +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}