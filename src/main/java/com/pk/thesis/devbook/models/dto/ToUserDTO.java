package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ToUserDTO {

    private ReducedUserDTO to;
    private Date date;
}
