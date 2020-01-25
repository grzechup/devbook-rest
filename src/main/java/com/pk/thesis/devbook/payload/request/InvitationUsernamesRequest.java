package com.pk.thesis.devbook.payload.request;

import lombok.Data;

@Data
public class InvitationUsernamesRequest {

    private String mainUsername;
    private String usernameToProcess;

}
