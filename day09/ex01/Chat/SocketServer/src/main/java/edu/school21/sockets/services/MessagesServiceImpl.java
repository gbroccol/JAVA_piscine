package edu.school21.sockets.services;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class MessagesServiceImpl implements MessagesService {

    MessagesRepository messagesRepository;

    @Autowired
    public MessagesServiceImpl(@Qualifier("messagesRepository") MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public void saveMessage(String text) throws SQLException {
        messagesRepository.save(new Message(text));
    }
}
