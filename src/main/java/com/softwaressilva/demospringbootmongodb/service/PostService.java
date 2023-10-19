package com.softwaressilva.demospringbootmongodb.service;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import com.softwaressilva.demospringbootmongodb.domain.User;
import com.softwaressilva.demospringbootmongodb.dto.PostDTO;
import com.softwaressilva.demospringbootmongodb.repository.PostRepository;
import com.softwaressilva.demospringbootmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserService userService;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Post não encontrado"));
    }

    public Post insert(Post obj) {
        userService.findById(obj.getAuthor().getId());
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Post update(Post obj) {
        Post entity = findById(obj.getId());
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(Post entity, Post obj) {
        entity.setTitle(obj.getTitle());
        entity.setBody(obj.getBody());
    }

    public Post fromDTO (PostDTO postDto) {
        return new Post(postDto.getId(), postDto.getDate(), postDto.getTitle(), postDto.getBody(), postDto.getAuthor());
    }
}
