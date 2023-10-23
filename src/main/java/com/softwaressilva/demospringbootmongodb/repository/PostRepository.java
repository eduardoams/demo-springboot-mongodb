package com.softwaressilva.demospringbootmongodb.repository;

import com.softwaressilva.demospringbootmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'body': { $regex: ?0, $options: 'i' } }")
    List<Post> searchBody(String text);

    @Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> searchAll(String text, Date minDate, Date maxDate);

    List<Post> findByTitleContainingIgnoreCase(String text);
}
