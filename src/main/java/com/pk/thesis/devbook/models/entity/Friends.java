package com.pk.thesis.devbook.models.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Friends {

    @Id
    @GeneratedValue()
    private long id;

    @ManyToOne()
    @JoinColumn(name="from_user_fk")
    private User from;

    @ManyToOne()
    @JoinColumn(name="to_user_fk")
    private User to;

    @Column(name = "friendship_date_start")
    private Date friendshipDateStart;

    public Friends(User from, User to, Date friendshipDateStart) {
        this.from = from;
        this.to = to;
        this.friendshipDateStart = friendshipDateStart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Date getFriendshipDateStart() {
        return friendshipDateStart;
    }

    public void setFriendshipDateStart(Date friendshipDateStart) {
        this.friendshipDateStart = friendshipDateStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friends friends = (Friends) o;
        return id == friends.id &&
                Objects.equals(from, friends.from) &&
                Objects.equals(to, friends.to) &&
                Objects.equals(friendshipDateStart, friends.friendshipDateStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, friendshipDateStart);
    }
}
