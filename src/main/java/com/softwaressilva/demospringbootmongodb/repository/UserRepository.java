package com.softwaressilva.demospringbootmongodb.repository;

import com.softwaressilva.demospringbootmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
