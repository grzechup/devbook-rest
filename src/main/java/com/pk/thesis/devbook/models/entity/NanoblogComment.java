package com.pk.thesis.devbook.models.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "nanoblog_comment")
public class NanoblogComment extends Comment {

    @OneToMany
    private Set<User> pluses;

    @OneToMany
    private Set<User> minuses;
}
