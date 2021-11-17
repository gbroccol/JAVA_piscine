package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;

import java.sql.SQLException;

//@SpringBootTest
public class UsersServiceImplTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    UsersService usersService = context.getBean("usersService", UsersService.class);

    @Test
    public void signUpTest() throws SQLException {

        String password;

        password = usersService.signUp("dlindgren@carter.com");
        Assertions.assertTrue(!password.isEmpty() && !password.equals("") && !password.equals(null));

        password = usersService.signUp("london92@hotmail.com");
        Assertions.assertTrue(!password.isEmpty() && !password.equals("") && !password.equals(null));

        password = usersService.signUp("andrew.romaguera@prosacco.com");
        Assertions.assertTrue(!password.isEmpty() && !password.equals("") && !password.equals(null));
    }
}
