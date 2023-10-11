package com.softwaressilva.demospringbootmongodb.repository;

import com.softwaressilva.demospringbootmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
