package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardCommentDTO {

    private Long id;
    private UsernameDTO user;
    private String content;
    private List<UsernameDTO> likes;
    private Date created;
}
