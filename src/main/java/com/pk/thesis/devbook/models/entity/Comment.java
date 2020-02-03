package com.pk.thesis.devbook.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Lob
    @Column
    private String content;

    @Column
    private Date created;

    @Column
    private Boolean isDeleted;

    public Comment(User user, String content){
        this.user=user;
        this.content = content;
        this.created = new Date();
        this.isDeleted = false;
    }

}


