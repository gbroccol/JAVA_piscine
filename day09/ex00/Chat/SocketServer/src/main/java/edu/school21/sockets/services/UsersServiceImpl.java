package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepository") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(String username, String password) throws SQLException {

//        if (user.getUsername() == null || user.getUsername().equals("")) {
//            System.err.println("ERROR. Username is required");
//            return;
//        }

//        String password = UUID.randomUUID().toString();
        usersRepository.save(new User(username, password));
        return;
    }
}