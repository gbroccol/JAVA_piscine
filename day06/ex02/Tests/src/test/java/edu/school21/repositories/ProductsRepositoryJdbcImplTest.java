package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {

    DataSource dataSource;
    ProductsRepository repository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS =
            Arrays.asList(
                    new Product(1L, "apple", 55),
                    new Product(2L, "grapes", 155),
                    new Product(3L, "lime", 34),
                    new Product(4L, "lemon", 64),
                    new Product(5L, "cherry", 23),
                    new Product(6L, "blueberry", 454),
                    new Product(7L, "banana", 463),
                    new Product(8L, "watermelon", 34),
                    new Product(9L, "melon", 56),
                    new Product(10L, "orange", 26),
                    new Product(11L, "pear", 568),
                    new Product(12L, "strawberry", 62));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(8);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "grapes", 15000000);
    final Product EXPECTED_SAVED_PRODUCT = new Product(13L, "lime", 50);

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId()).get());
        Assertions.assertEquals(Optional.empty(), repository.findById(42l));
    }

    @Test
    public void findAllTest() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    public void updateTest() {
        repository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(EXPECTED_UPDATED_PRODUCT.getId()).get());
    }

    @Test
    public void savedTest() {
        repository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(EXPECTED_SAVED_PRODUCT.getId()).get());
    }

    @Test
    public void deleteTest() {
        repository.delete(EXPECTED_FIND_ALL_PRODUCTS.get(5).getId());
        Assertions.assertFalse(repository.findById(EXPECTED_FIND_ALL_PRODUCTS.get(5).getId()).isPresent());
    }
}
