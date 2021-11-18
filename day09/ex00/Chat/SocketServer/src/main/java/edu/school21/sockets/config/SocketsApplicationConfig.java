package edu.school21.sockets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("edu.school21.sockets")
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {

    @Autowired
    public Environment env;

    @Bean
    public DriverManagerDataSource driverManagerDataSource() {

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(env.getProperty("db.url"));
        driverManagerDataSource.setUsername(env.getProperty("db.user"));
        driverManagerDataSource.setPassword(env.getProperty("db.password"));
        driverManagerDataSource.setDriverClassName(env.getProperty("db.driver.name"));
        return driverManagerDataSource;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
