package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;
    private ReducedUserDTO user;
    private String content;
    private Date created;
    private Boolean isDeleted;

}
