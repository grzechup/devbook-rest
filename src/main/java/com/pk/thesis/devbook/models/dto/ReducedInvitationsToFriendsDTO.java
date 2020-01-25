package com.pk.thesis.devbook.models.dto;

import lombok.Data;

@Data
public class ReducedInvitationsToFriendsDTO {

    private ReducedUserDTO from;
    private ReducedUserDTO to;
}
