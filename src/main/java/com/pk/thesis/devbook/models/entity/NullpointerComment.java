package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "nullpointer_comment")
public class NullpointerComment  extends Comment {

    @ManyToMany(mappedBy = "pointedUpNullpointerComments")
    private List<User> pointsUp;

    @ManyToMany(mappedBy = "pointedDownNullpointerComments")
    private List<User> pointsDown;

    public NullpointerComment(User user, String content){
        super(user,content);
        this.pointsUp = Collections.emptyList();
        this.pointsDown = Collections.emptyList();
    }

    public NullpointerComment(){}
}
