package com.softwaressilva.demospringbootmongodb.repository;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
