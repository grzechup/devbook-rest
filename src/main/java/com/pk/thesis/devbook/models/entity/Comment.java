package com.pk.thesis.devbook.models.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private String content;

}


