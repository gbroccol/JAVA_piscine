package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {

        final String selectMessageId = "SELECT * FROM chat.message WHERE messageID = " + id;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(selectMessageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            Message message = new Message(resultSet.getLong(1),
                                            resultSet.getString(4),
                                            resultSet.getTimestamp(5).toLocalDateTime());

            Long userId =  resultSet.getLong(2);
            Long chatId = resultSet.getLong(3);

            String selectUserId =  "SELECT * FROM chat.user WHERE userID = " + userId;
            String selectChatRoomId =  "SELECT * FROM chat.chatroom WHERE chatID = " + chatId;

            preparedStatement = connection.prepareStatement(selectUserId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            message.setAuthor(new User(userId, resultSet.getString(2), resultSet.getString(3)));

            preparedStatement = connection.prepareStatement(selectChatRoomId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            message.setChatroom(new Chatroom(chatId, resultSet.getString(2)));
            resultSet.close();
            preparedStatement.close();
            return Optional.of(message);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) {

        Long authorId;
        Long chatId;

        if (message.getAuthor() != null && message.getChatroom() != null) {
            authorId = message.getAuthor().getUserId();
            chatId = message.getChatroom().getChatId();
        } else {
            throw new NotSavedSubEntityException();
        }

        String selectAuthorId =  "SELECT * FROM chat.user WHERE userID = " + authorId;
        String selectChatRoomId =  "SELECT * FROM chat.chatroom WHERE chatID = " + chatId;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(selectAuthorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("AuthorId " + authorId + " not exists");
                throw new NotSavedSubEntityException();
            }

            preparedStatement = connection.prepareStatement(selectChatRoomId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("ChatRoomId " + chatId + " not exists");
                throw new NotSavedSubEntityException();
            }

            String text;
            if (message.getText() == null || message.getText().equals("")) {
                System.out.println("Message is empty");
                return;
            } else {
                text = "\'" + message.getText() + "\'";
            }

            String localDateTime = "'null'";
            if (message.getLocalDateTime() != null) {
                localDateTime = "'" + Timestamp.valueOf(message.getLocalDateTime()) + "'";
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO chat.message (author, chatRoom, text, localDateTime) " +
                            "VALUES (" + authorId + ", " + chatId + ", " +  text + ", " + localDateTime + ") RETURNING messageID");

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            message.setMessageId(resultSet.getLong(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
