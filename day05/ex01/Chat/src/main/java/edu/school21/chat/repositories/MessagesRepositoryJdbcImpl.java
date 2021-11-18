package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
