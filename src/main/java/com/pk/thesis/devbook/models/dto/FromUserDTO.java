package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FromUserDTO {

    private ReducedUserDTO from;
    private Date date;

}
