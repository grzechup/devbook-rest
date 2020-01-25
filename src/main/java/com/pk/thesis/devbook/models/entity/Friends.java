package com.pk.thesis.devbook.models.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
public class Friends {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="from_user_fk")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="to_user_fk")
    private User to;

    @Column(name = "friendship_date_start")
    private Date friendshipDateStart;

    public Friends(User from, User to) {
        this.from = from;
        this.to = to;
    }

}
