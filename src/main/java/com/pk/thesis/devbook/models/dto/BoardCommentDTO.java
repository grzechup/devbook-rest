package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardCommentDTO extends CommentDTO {

    private List<UsernameDTO> likes;
}
