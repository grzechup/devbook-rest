package com.pk.thesis.devbook.models.dto;

import com.pk.thesis.devbook.models.ERole;
import com.pk.thesis.devbook.models.entity.BoardPost;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private List<ToUserDTO> friendsOf;
    private List<FromUserDTO> friends;
    private List<ToUserDTO> invitedFriends;
    private List<FromUserDTO> invitationsToFriends;

    private List<BoardPost> boardPosts;
    private List<BoardPost> likedBoards;
    private Long experience;
    private Set<ERole> roles;

}
