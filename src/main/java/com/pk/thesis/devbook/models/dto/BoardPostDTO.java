package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardPostDTO {

    private Long id;
    private ReducedUserDTO user;
    private Date created;
    private String content;
    private List<UsernameDTO> likes;
}
