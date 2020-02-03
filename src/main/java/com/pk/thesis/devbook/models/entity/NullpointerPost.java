package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "nullpointer_post")
public class NullpointerPost extends Post {

    private String title;

    @ManyToMany(mappedBy = "awardedNullpointerPosts", cascade = CascadeType.ALL)
    private List<User> points;

    @ManyToMany(mappedBy = "viewedNullpointerPosts", cascade = CascadeType.ALL)
    private List<User> views;

    @OneToMany()
    @JoinColumn(name = "nullpointer_comments", referencedColumnName = "id")
    private List<NullpointerComment> comments;

    //TODO: lista tagow
    //private List<String> tags;

    private boolean isResolved;

    public NullpointerPost(User user, String title, String content, Date created){
        this.user = user;
        this.title = title;
        this.content = content;
        this.created = created;
        this.views = Collections.emptyList();
        this.points = Collections.emptyList();
    }

    public NullpointerPost(){ }
}
