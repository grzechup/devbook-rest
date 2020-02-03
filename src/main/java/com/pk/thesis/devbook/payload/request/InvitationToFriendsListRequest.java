package com.pk.thesis.devbook.payload.request;

import lombok.Data;

@Data
public class InvitationToFriendsListRequest {

    private String mainUsername;
    private String usernameToProcess;

}
