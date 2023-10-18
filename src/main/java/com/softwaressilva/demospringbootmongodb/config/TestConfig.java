package com.softwaressilva.demospringbootmongodb.config;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import com.softwaressilva.demospringbootmongodb.domain.User;
import com.softwaressilva.demospringbootmongodb.repository.PostRepository;
import com.softwaressilva.demospringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User u1 = new User(null, "Eduardo", "eduardo@gmail.com");
        User u2 = new User(null, "Amanda", "amanda@gmail.com");
        User u3 = new User(null, "Chloe", "chloe@gmail.com");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Post p1 = new Post(null, sdf.parse("01/09/1910"), "Vai Corinthians!", "Eternamente dentro de nossos corações", u1);
        Post p2 = new Post(null, sdf.parse("01/06/2023"), "Bom dia", "Vamos com tudo, Fiel", u2);

        postRepository.saveAll(Arrays.asList(p1, p2));
    }
}
