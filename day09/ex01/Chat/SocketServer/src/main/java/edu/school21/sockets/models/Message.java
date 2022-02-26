package edu.school21.sockets.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private String text;

    private LocalDateTime date;

    public Message(String text, LocalDateTime date) {
        this.text = text;
        this.date = date;
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(text, message.text) && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, date);
    }

    @Override
    public String toString() {
        return "Message { " +
                "text = '" + text + '\'' +
                ", date = " + date +
                '}';
    }
}
