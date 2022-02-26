package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component ("usersRepository")
public class UsersRepositoryImpl implements UsersRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM day09.user WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM day09.user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO day09.user (username, password) VALUES (?, ?);", user.getUsername(), user.getPassword());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE day09.user SET username = ? WHERE id = ?", // add password
                user.getUsername(),
                user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM day09.user WHERE id = ? ", id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = (User)jdbcTemplate.query("SELECT * FROM day09.user WHERE username = ?",
                        new Object[]{username},
                        new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
        if (user == null)
            return Optional.empty();
        return Optional.of(user);
    }
}