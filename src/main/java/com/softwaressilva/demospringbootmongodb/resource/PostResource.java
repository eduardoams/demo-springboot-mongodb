package com.softwaressilva.demospringbootmongodb.resource;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import com.softwaressilva.demospringbootmongodb.dto.PostDTO;
import com.softwaressilva.demospringbootmongodb.resource.util.URL;
import com.softwaressilva.demospringbootmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.Date;
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

    @GetMapping(value = "/search-title")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        List<Post> list = postService.findByTitle(URL.decodeParam(text));
        List<PostDTO> listDto = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/search-body")
    public ResponseEntity<List<PostDTO>> bodySearch(@RequestParam(value = "text", defaultValue = "") String text) {
        List<Post> list = postService.searchBody(URL.decodeParam(text));
        List<PostDTO> listDto = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/search-all")
    public ResponseEntity<List<PostDTO>> bodySearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(1L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Post> list = postService.searchAll(text, min, max);
        List<PostDTO> listDto = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
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
