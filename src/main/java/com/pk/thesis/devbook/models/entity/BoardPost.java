package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "board_post")
public class BoardPost extends Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id" , nullable = false)
    private Long userId;

/*    @Column(name = "photo_id")
    private Long photoId;*/

    @Column(name = "content" , nullable = false)
    private String content;

    @ManyToMany(mappedBy = "likedBoards")
    private Set<User> likes;

    @OneToMany()
    @JoinColumn(name = "board_comments", referencedColumnName = "id")
    private Set<BoardComment> comments;


}
