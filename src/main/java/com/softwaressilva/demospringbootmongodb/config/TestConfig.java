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

        User u1 = new User(null, "Eduardo", "eduardo@gmail.com", "11122233344", "112223334", "123456");
        User u2 = new User(null, "Amanda", "amanda@gmail.com", "22233344455", "223334445", "123456");
        User u3 = new User(null, "Chloe", "chloe@gmail.com", "33344455566", "334445556", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));
    }
}
