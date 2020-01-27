package com.pk.thesis.devbook.models.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique=true, nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
