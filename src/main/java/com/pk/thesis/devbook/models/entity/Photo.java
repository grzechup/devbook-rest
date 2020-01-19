package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Photo  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "path")
    private String path;
}
