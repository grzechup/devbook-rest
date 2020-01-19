package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name="nanoblog_post")
public class NanoblogPost extends  Post{

    @OneToMany
    private Set<User> pluses;

    @OneToMany
    private Set<User> minuses;

}
