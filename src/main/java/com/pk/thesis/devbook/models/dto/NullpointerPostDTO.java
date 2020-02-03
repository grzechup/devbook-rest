package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NullpointerPostDTO {

    private Long id;
    private String title;
    private List<UsernameDTO> points;
    private List<UsernameDTO> views;
    private boolean isResolved;
    private ReducedUserDTO user;
    private String content;
    private Date created;
}
