package com.pk.thesis.devbook.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "board_comment")
public class BoardComment extends Comment {

    @Column
    @OneToMany
    private List<User> likes;

    public BoardComment(User user, String content) {
        super(user, content);
    }

    public BoardComment() {
        super();
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }
}
