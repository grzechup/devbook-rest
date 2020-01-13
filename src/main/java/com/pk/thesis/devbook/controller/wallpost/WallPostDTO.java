package com.pk.thesis.devbook.controller.wallpost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WallPostDTO {

    private Long authorId;
    private String content;
}
