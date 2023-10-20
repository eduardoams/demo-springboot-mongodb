package com.softwaressilva.demospringbootmongodb.repository;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'body': { $regex: ?0, $options: 'i' } }")
    List<Post> bodySearch(String text);

    List<Post> findByTitleContainingIgnoreCase(String text);
}
