package com.pk.thesis.devbook.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class NanoblogPostDTO {

    private UsernameDTO pluses;
    private UsernameDTO minuses;
    private List<NanoblogCommentDTO> comments;
    private String content;
    private Date created;
    private ReducedUserDTO user;

}


