package com.pk.thesis.devbook.models.dto;

import com.pk.thesis.devbook.models.ERole;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class FullUserDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private List<ToUserDTO> friendsAccepted;
    private List<FromUserDTO> friends;
    private List<ToUserDTO> invitedFriends;
    private List<FromUserDTO> invitationsToFriends;

    private List<BoardPostDTO> boardPosts;
    private List<BoardPostDTO> likedBoards;
    private Long experience;
    private Set<ERole> roles;

}
