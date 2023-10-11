package com.softwaressilva.demospringbootmongodb.resources;

import com.softwaressilva.demospringbootmongodb.domain.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User eduardo = new User("1", "Eduardo Souza", "eduardo@gmail.com");
        User amanda = new User("2", "Amanda Souza", "amanda@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(eduardo, amanda));
        return ResponseEntity.ok().body(list);
    }
}
