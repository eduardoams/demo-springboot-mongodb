package com.softwaressilva.demospringbootmongodb.resource;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import com.softwaressilva.demospringbootmongodb.domain.User;
import com.softwaressilva.demospringbootmongodb.dto.CommentDTO;
import com.softwaressilva.demospringbootmongodb.dto.PostDTO;
import com.softwaressilva.demospringbootmongodb.dto.UserDTO;
import com.softwaressilva.demospringbootmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody PostDTO objDto) {
        Post obj = postService.fromDTO(objDto);
        obj = postService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody PostDTO objDto) {
        Post obj = postService.fromDTO(objDto);
        obj.setId(id);
        obj = postService.update(obj);
        return ResponseEntity.ok().body(obj);
    }
}
