package com.pk.thesis.devbook.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class NullpointerPostWithCommentsDTO extends NullpointerPostDTO{

    private List<NullpointerCommentDTO> nullpointerComments;
}
