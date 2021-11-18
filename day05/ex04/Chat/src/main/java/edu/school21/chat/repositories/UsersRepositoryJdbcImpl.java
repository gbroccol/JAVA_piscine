package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {

        List <User> listUser = new ArrayList();

        if (page <= 0 || size <= 0) {
            return listUser;
        }

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT u.*, fl_cr.chatname AS followedchatname, fl_cr.chatowner AS followedchatownerid\n" +
                            "FROM chat.chatroom fl_cr\n" +
                            "RIGHT JOIN (\n" +
                            "\t\t\t\tSELECT usr_cr.userid\n" +
                            "\t\t\t\t\t\t, usr_cr.login\n" +
                            "\t\t\t\t\t\t, usr_cr.\"password\"\n" +
                            "\t\t\t\t\t\t, usr_cr.mychatroomid\n" +
                            "\t\t\t\t\t\t, usr_cr.mychatroomname\n" +
                            "\t\t\t\t\t\t, chats.roomid AS followedchatid\n" +
                            "\t\t\t\tFROM chat.chats chats\n" +
                            "\t\t\t\tRIGHT JOIN (\n" +
                            "\t\t\t\t\t\t\t\tSELECT\t\n" +
                            "\t\t\t\t\t\t\t\t\t\tui.userid\n" +
                            "\t\t\t\t\t\t\t\t\t\t, ui.login\n" +
                            "\t\t\t\t\t\t\t\t\t\t, ui.\"password\"\n" +
                            "\t\t\t\t\t\t\t\t\t\t, cr.chatid AS mychatroomid\n" +
                            "\t\t\t\t\t\t\t\t\t\t, cr.chatname as mychatroomname\n" +
                            "\t\t\t\t\t\t\t\tFROM chat.chatroom cr\n" +
                            "\t\t\t\t\t\t\t\tRIGHT JOIN (\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\tSELECT \n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tuserid\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t, login\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t, \"password\"\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\tFROM chat.user\n" +
                            "\t\t\t\t\t\t\t\t\t\t\t\tlimit ? offset ?\n" +
                            "\t\t\t\t\t\t\t\t) AS ui ON ui.userid = cr.chatowner\n" +
                            "\t\t\t\t) AS usr_cr ON usr_cr.userid = chats.userid\n" +
                            "\t\t\t) AS u ON u.followedchatid = fl_cr.chatid\n" +
                            "ORDER BY userid, mychatroomid, followedchatid\n"
            );

            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean continue_search = true;

                while (continue_search) {
                    User user = new User();

                    Long userId = resultSet.getLong("userID");

                    user.setUserId(resultSet.getLong("userID"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));

                    List <Chatroom> listMyChatRoom = new ArrayList();

                    List <Chatroom> listFollowedChatRoom = new ArrayList();

                    do {
                        Long idMyChatRoom = resultSet.getLong("mychatroomid");

                        if (idMyChatRoom != 0l && resultSet.getString("mychatroomname") != null) {
                            int i;

                            for (i = 0; i < listMyChatRoom.size(); i++) {
                                if (listMyChatRoom.get(i).getChatId() == idMyChatRoom) {
                                    break;
                                }
                            }

                            if (listMyChatRoom.size() == i) {
                                Chatroom created = new Chatroom();

                                created.setChatId(idMyChatRoom);
                                created.setChatName(resultSet.getString("mychatroomname"));
                                listMyChatRoom.add(created);
                            }
                        }

                        Long idFollowedChatRoom = resultSet.getLong("followedchatid");

                        String nameFollowedChatRoom = resultSet.getString("followedchatname");

                        if (idFollowedChatRoom != 0l && nameFollowedChatRoom != null) {

                            int i;

                            for (i = 0; i < listFollowedChatRoom.size(); i++) {
                                if (listFollowedChatRoom.get(i).getChatId() == idFollowedChatRoom) {
                                    break;
                                }
                            }

                            if (listFollowedChatRoom.size() == i) {
                                Chatroom followed = new Chatroom();

                                followed.setChatId(idFollowedChatRoom);
                                followed.setChatName(nameFollowedChatRoom);
                                listFollowedChatRoom.add(followed);
                            }
                        }

                        user.setCreatedRooms(listMyChatRoom);
                        user.setActiveChatroom(listFollowedChatRoom);

                        if (!resultSet.next()) {
                            continue_search = false;
                            break;
                        }

                    } while (userId == resultSet.getLong("userID"));
                    listUser.add(user);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listUser;
    }
}
