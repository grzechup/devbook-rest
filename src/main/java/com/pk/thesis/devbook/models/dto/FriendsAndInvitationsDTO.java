package com.pk.thesis.devbook.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FriendsAndInvitationsDTO {

    private List<FromUserDTO> friends;
    private List<ToUserDTO> friendsAccepted;
    private List<FromUserDTO> invitationsToFriends;
    private List<ToUserDTO> invitedFriends;

}
