package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

import static edu.school21.chat.app.Program.ANSI_GREEN;
import static edu.school21.chat.app.Program.ANSI_RESET;

public class Chatroom {
    private Long            chatId;
    private String          chatName;
    private User            chatOwner;
    private List<Message>   messages;

    public Chatroom(Long chatId, String chatName, User chatOwner, List<Message> messages) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.chatOwner = chatOwner;
        this.messages = messages;
    }

    public Chatroom(String chatName, User chatOwner, List<Message> messages) {
        this(0L, chatName, chatOwner, messages);
    }

    public Chatroom(Long chatId, String chatName) {
        this(chatId, chatName, null, null);
    }

    public Chatroom() {
        this(0l, "", null, null);
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public User getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(User chatOwner) {
        this.chatOwner = chatOwner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatId.equals(chatroom.chatId) && chatName.equals(chatroom.chatName) && chatOwner.equals(chatroom.chatOwner) && messages.equals(chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, chatName, chatOwner, messages);
    }

    @Override
    public String toString() {
        return ANSI_GREEN + "room {" +
                "id = " + chatId +
                ", name = " + chatName +
                ", creator = " + chatOwner +
                ", messages = " + messages +
                '}' + ANSI_RESET;
    }
}
