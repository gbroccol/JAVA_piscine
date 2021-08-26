package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

import javax.sql.DataSource;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {

        Message message = new Message(id, 0l, 0l, "", null, null, null);

        final String selectMessageById = "SELECT * FROM messages WHERE id = " + id;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(selectMessageById);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            Long authorId =  resultSet.getLong(2);
            Long chatroomId = resultSet.getLong(3);

            message.setAuthorId(authorId);
            message.setChatroomId(chatroomId);
            message.setText(resultSet.getString(4));
            message.setDate(resultSet.getDate(5));

            // select author
            String selectAuthorById =  "SELECT * FROM users WHERE id = " + authorId;
            preparedStatement = dataSource.getConnection().prepareStatement(selectAuthorById);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            message.setAuthor(new User(authorId,
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                            null,
                          null));


            // select chatroom
            String selectChatRoomId =  "SELECT * FROM chatrooms WHERE id = " + chatroomId;
            preparedStatement = dataSource.getConnection().prepareStatement(selectChatRoomId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            message.setChatroom(new Chatroom(chatroomId,
                                            resultSet.getString(2),
                                            resultSet.getInt(3),
                                    null,
                                      null));


            resultSet.close();
            preparedStatement.close();
            return Optional.of(message);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
