package com.pk.thesis.devbook.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "board_post")
public class BoardPost extends Post {

    @ManyToOne
    private User user;

    private Date created;

/*    @Column(name = "photo_id")
    private Long photoId;*/
    @ManyToMany(mappedBy = "likedBoards", cascade = CascadeType.ALL)
    private List<User> likes = new ArrayList<>();

    @OneToMany()
    @JoinColumn(name = "board_comments", referencedColumnName = "id")
    private List<BoardComment> comments;

    private String content;

    public BoardPost(String content, User user, Date created){
        super();
        this.content = content;
        this.user = user;
        this.created = created;
    }
}
