package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "board_comment")
public class BoardComment extends Comment {

    @Column
    @OneToMany
    private Set<User> likes;


}
