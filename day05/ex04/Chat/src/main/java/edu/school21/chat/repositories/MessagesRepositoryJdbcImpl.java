package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private boolean validMessage(Message message, Connection connection) {
        Long authorId;
        Long chatId;

        if (message.getAuthor() != null && message.getChatroom() != null) {
            authorId = message.getAuthor().getUserId();
            chatId = message.getChatroom().getChatId();
        } else {
            return false;
        }

        String selectAuthorId =  "SELECT * FROM chat.user WHERE userID = " + authorId;
        String selectChatRoomId =  "SELECT * FROM chat.chatroom WHERE chatID = " + chatId;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectAuthorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("AuthorId " + authorId + " not exists");
                return false;
            }

            preparedStatement = connection.prepareStatement(selectChatRoomId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("ChatRoomId " + chatId + " not exists");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public Optional<Message> findById(Long id) {

        final String selectMessageId = "SELECT * FROM chat.message WHERE messageID = " + id;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(selectMessageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            Message message = new Message(resultSet.getLong(1),
                                            resultSet.getString(4),
                                            resultSet.getTimestamp(5) == null ? null : resultSet.getTimestamp(5).toLocalDateTime());

            Long userId =  resultSet.getLong(2);
            Long chatId = resultSet.getLong(3);

            String selectUserId =  "SELECT * FROM chat.user WHERE userID = " + userId;
            String selectChatRoomId =  "SELECT * FROM chat.chatroom WHERE chatID = " + chatId;

            preparedStatement = connection.prepareStatement(selectUserId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            message.setAuthor(new User(userId, resultSet.getString(2), resultSet.getString(3)));

            preparedStatement = connection.prepareStatement(selectChatRoomId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            message.setChatroom(new Chatroom(chatId, resultSet.getString(2)));
            return Optional.of(message);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) {

        try (Connection connection = dataSource.getConnection()) {

            if (!validMessage(message, connection)) {
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO chat.message (author, chatRoom, text, localDateTime) VALUES (?, ?, ?, ?) RETURNING messageID;");

            preparedStatement.setLong(1, message.getAuthor().getUserId());
            preparedStatement.setLong(2, message.getChatroom().getChatId());
            preparedStatement.setString(3, message.getText() == null ? "" : message.getText());
            if (message.getLocalDateTime() != null) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(Timestamp.valueOf(message.getLocalDateTime()).toString()));
            } else {
                preparedStatement.setDate(4, null);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            message.setMessageId(resultSet.getLong(1));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            String selectMessageById =  "SELECT * FROM chat.message WHERE messageID = " + message.getMessageId();

            PreparedStatement preparedStatement = connection.prepareStatement(selectMessageById);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                save(message);
                return;
            }

            if (validMessage(message, connection)) {
                return;
            }

            preparedStatement = connection.prepareStatement(
                    "UPDATE chat.message SET author = ?, chatRoom = ?, text = ?, localDateTime = ? WHERE messageID = ?;");

            preparedStatement.setLong(1,message.getAuthor().getUserId());
            preparedStatement.setLong(2,message.getChatroom().getChatId());
            preparedStatement.setString(3, message.getText() == null ? "" : message.getText());
            if (message.getLocalDateTime() != null) {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(Timestamp.valueOf(message.getLocalDateTime()).toString()));
//                preparedStatement.setDate(4, java.sql.Date.valueOf(Timestamp.valueOf(message.getLocalDateTime()).toString()));
            } else {
                preparedStatement.setDate(4, null);
            }
            preparedStatement.setLong(5, message.getMessageId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
