package com.pk.thesis.devbook.controller.wallpost;

import com.pk.thesis.devbook.utils.StringListConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "wall_post")
public class WallPost {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String content;

    @Convert(converter = StringListConverter.class)
    private List<String> keywords;

}
