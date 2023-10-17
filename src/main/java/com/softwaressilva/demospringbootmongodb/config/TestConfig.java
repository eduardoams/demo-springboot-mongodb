package com.softwaressilva.demospringbootmongodb.config;

import com.softwaressilva.demospringbootmongodb.domain.User;
import com.softwaressilva.demospringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User u1 = new User(null, "Eduardo", "eduardo@gmail.com");
        User u2 = new User(null, "Amanda", "amanda@gmail.com");
        User u3 = new User(null, "Chloe", "chloe@gmail.com");
        User u4 = new User(null, "Spazio", "spazio@gmail.com");

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
    }
}
