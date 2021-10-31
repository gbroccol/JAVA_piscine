package edu.school21.repositories;

import edu.school21.models.Product;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    DataSource          dataSource;
    ResultSet           resultSet;
    PreparedStatement   preparedStatement;
    Connection          connection;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM test.product WHERE identifier = " + id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            Product product = new Product(resultSet.getLong(1),
                                            resultSet.getString(2),
                                            resultSet.getInt(3));
            preparedStatement.close();
            resultSet.close();
            connection.close();
            return Optional.of(product);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {

        List<Product> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM test.product");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("identifier");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                list.add(new Product(id, name, price));
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list.isEmpty()?null:list;
    }

    @Override
    public void update(Product product) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE test.product SET name = \'" + product.getName() + "\', price = " + product.getPrice() + " WHERE identifier = " + product.getId());
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO test.product (name, price) VALUES (\'" + product.getName() + "\', " + product.getPrice() + ")");
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM test.product WHERE identifier = " + id);
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        };
    }
}
