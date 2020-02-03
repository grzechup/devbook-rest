package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class NullpointerCommentDTO extends CommentDTO {

    private List<UsernameDTO> pointsUp;
    private List<UsernameDTO> pointsDown;
    private List<UsernameDTO> views;

}
