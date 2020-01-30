package com.pk.thesis.devbook.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NanoblogCommentDTO {

    //TODO: zrobic wspolne dto dla komentarzy i postow bo nie trzeba akurat dto'sow robic osobnych
    private ReducedUserDTO user;
    private String content;
    private Date created;
}
