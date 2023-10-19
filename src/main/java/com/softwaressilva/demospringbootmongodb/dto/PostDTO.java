package com.softwaressilva.demospringbootmongodb.dto;

import com.softwaressilva.demospringbootmongodb.domain.Post;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Instant date;
    private String title;
    private String body;
    private AuthorDTO author;

    private List<CommentDTO> comments = new ArrayList<>();
    
    public PostDTO() {
    }
    
    public PostDTO(Post obj) {
        id = obj.getId();
        date = obj.getDate();
        title = obj.getTitle();
        body = obj.getBody();
        comments = obj.getComments();
        author = obj.getAuthor();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }
}
