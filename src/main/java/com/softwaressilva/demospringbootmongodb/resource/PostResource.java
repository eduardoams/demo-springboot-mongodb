package com.softwaressilva.demospringbootmongodb.resource;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import com.softwaressilva.demospringbootmongodb.dto.PostDTO;
import com.softwaressilva.demospringbootmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll() {
        List<Post> list = postService.findAll();
        List<PostDTO> listDto = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(new PostDTO(obj));
    }
}
