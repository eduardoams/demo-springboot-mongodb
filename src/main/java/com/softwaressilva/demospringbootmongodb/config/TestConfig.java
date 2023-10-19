package com.softwaressilva.demospringbootmongodb.config;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import com.softwaressilva.demospringbootmongodb.domain.User;
import com.softwaressilva.demospringbootmongodb.dto.AuthorDTO;
import com.softwaressilva.demospringbootmongodb.dto.CommentDTO;
import com.softwaressilva.demospringbootmongodb.repository.PostRepository;
import com.softwaressilva.demospringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User u1 = new User(null, "Eduardo", "eduardo@gmail.com");
        User u2 = new User(null, "Amanda", "amanda@gmail.com");
        User u3 = new User(null, "Chloe", "chloe@gmail.com");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Post p1 = new Post(null, Instant.now(), "Vai Corinthians!", "Eternamente dentro de nossos corações", new AuthorDTO(u1));
        Post p2 = new Post(null, Instant.now(), "Bom dia", "Vamos com tudo, Fiel", new AuthorDTO(u2));
        Post p3 = new Post(null, Instant.now(), "Neo Química Arena", "A Arena mais bonita do Brasil", new AuthorDTO(u1));

        CommentDTO c1 = new CommentDTO("O melhor do Brasil!", Instant.now(), new AuthorDTO(u2));
        CommentDTO c2 = new CommentDTO("Concordo, mas existe outros estádios no Brasil que também são bonitos", Instant.now(), new AuthorDTO(u2));
        CommentDTO c3 = new CommentDTO("Não tem como discordar...", Instant.now(), new AuthorDTO(u3));

        p1.getComments().add(c1);
        p3.getComments().addAll(Arrays.asList(c2, c3));

        //Salvando Post, aninhando Author e Comments à coleção deste objeto
        postRepository.saveAll(Arrays.asList(p1, p2, p3));

        //Referenciando Post à coleção do objeto User
        u1.getPosts().addAll(Arrays.asList(p1, p3));
        u2.getPosts().add(p2);

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
