package com.pk.thesis.devbook.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private Long authorId;

    private Long postId;

    private String content;

    private Date createDate;
}
