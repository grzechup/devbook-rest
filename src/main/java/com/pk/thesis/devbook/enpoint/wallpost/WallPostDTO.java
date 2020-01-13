package com.pk.thesis.devbook.enpoint.wallpost;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WallPostDTO {

    private Long authorId;
    private String content;
}
