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
/*    @Column(name = "photo_id")
    private Long photoId;*/

    @OneToMany()
    @JoinColumn(name = "board_comments", referencedColumnName = "id")
    private List<BoardComment> comments;

    @ManyToMany(mappedBy = "likedBoards", cascade = CascadeType.ALL)
    private List<User> likes = new ArrayList<>();

    public BoardPost(String content, User user, Date created){
        super();
        this.content = content;
        this.user = user;
        this.created = created;
    }
}
