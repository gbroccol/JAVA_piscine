package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepository") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean signUp(String username, String password) {

        if (!usersRepository.findByUsername(username).equals(Optional.empty())) {
            return false;
        }
        usersRepository.save(new User(username, passwordEncoder.encode(password)));
        return true;
    }

    @Override
    public boolean passwordCorrect(String username, String password) {
        if (usersRepository.findByUsername(username).equals(Optional.empty())) {
            return false;
        }
        User user = usersRepository.findByUsername(username).get();
        return passwordEncoder.matches(password, user.getPassword());
    }

}