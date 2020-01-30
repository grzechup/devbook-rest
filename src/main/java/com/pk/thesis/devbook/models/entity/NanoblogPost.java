package com.pk.thesis.devbook.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name="nanoblog_post")
public class NanoblogPost extends  Post{

    @OneToMany
    private Set<User> pluses;

    @OneToMany
    private Set<User> minuses;

    @OneToMany()
    @JoinColumn(name = "nanoblog_comments", referencedColumnName = "id")
    private List<NanoblogComment> comments;

    public NanoblogPost(User user, String content, Date created){
        this.user = user;
        this.content=content;
        this.created = created;
    }
}
