package com.pk.thesis.devbook.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name = "nullpointer_comment")
public class NullpointerComment  extends Comment {

    @OneToMany
    private Set<User> points;

    public NullpointerComment(User user, String content){
        super(user,content);
    }
}
