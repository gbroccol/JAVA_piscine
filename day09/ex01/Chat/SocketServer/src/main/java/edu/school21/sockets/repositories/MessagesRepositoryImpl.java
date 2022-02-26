package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component ("messagesRepository")
public class MessagesRepositoryImpl implements MessagesRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public MessagesRepositoryImpl(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Message findById(Long id) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message entity) {
        jdbcTemplate.update("INSERT INTO day09.messages (textMessage, dateTime) VALUES (?, now());", entity.getText());

    }

    @Override
    public void update(Message entity) {
    }

    @Override
    public void delete(Long id) {
    }
}