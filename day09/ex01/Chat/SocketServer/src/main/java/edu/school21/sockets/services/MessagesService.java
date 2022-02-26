package edu.school21.sockets.services;

import java.sql.SQLException;

public interface MessagesService {

    void saveMessage(String text) throws SQLException;

}
