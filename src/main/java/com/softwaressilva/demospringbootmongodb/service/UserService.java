package com.softwaressilva.demospringbootmongodb.service;

import com.softwaressilva.demospringbootmongodb.domain.User;
import com.softwaressilva.demospringbootmongodb.dto.UserDTO;
import com.softwaressilva.demospringbootmongodb.repository.UserRepository;
import com.softwaressilva.demospringbootmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj) {
        User entity = findById(obj.getId());
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    public User fromDTO (UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
