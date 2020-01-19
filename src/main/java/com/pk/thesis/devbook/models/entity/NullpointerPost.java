package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "nullpointer_post")
public class NullpointerPost extends Post {

    @OneToMany
    private Set<User> points;

    private Long views;
}
