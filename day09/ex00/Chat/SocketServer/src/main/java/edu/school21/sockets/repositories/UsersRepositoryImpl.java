package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component ("usersRepository")
public class UsersRepositoryImpl implements UsersRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DriverManagerDataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM public.socket_user WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM public.socket_user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO public.socket_user (username, password) VALUES (?, ?);", user.getUsername(), user.getPassword());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE public.socket_user SET email = ? WHERE identifier = ?",
                user.getUsername(),
                user.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM public.socket_user WHERE id = ? ", id);
    }
}