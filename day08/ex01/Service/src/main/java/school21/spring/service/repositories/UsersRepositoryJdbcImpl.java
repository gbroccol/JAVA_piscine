package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource          dataSource;
    Connection          connection;
    PreparedStatement   preparedStatement;
    ResultSet           resultSet;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.dataSource = ds;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM public.user WHERE identifier = ";
        try {
            preparedStatement = connection.prepareStatement(sql + id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        User user = null;
        for (long id = 1; (user = findById(id)) != null; id++) {
            list.add(user);
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    @Override
    public void save(User entity) {
        String sql = "INSERT INTO public.user (email) VALUES ('%s');";
        try {
            preparedStatement = connection.prepareStatement(String.format(sql, entity.getEmail()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE public.user SET email = ";
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql + '\'' + entity.getEmail() + '\'' + " WHERE identifier = " + entity.getIdentifier());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM public.user WHERE identifier = ";
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql + id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM public.user WHERE email = ";
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql + '\'' + email + '\'');
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1),
                                        resultSet.getString(2));
                return Optional .of(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
