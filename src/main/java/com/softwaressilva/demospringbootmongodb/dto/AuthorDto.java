package com.softwaressilva.demospringbootmongodb.dto;

import com.softwaressilva.demospringbootmongodb.domain.User;

import java.io.Serializable;
import java.security.PublicKey;

public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDto() {
    }

    public AuthorDto(User obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
