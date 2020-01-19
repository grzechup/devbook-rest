package com.pk.thesis.devbook.models.entity;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class Post {

    @Id
    @Column
    private Long id;

    private String content;

    @OneToMany
    @JoinColumn(name = "comments", referencedColumnName = "id")
    private Set<BoardComment> comments;



}
