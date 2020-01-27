package com.pk.thesis.devbook.payload.request;

import lombok.Data;

@Data
public class NewBoardCommentRequest {

    private Long id;
    private String content;
}
