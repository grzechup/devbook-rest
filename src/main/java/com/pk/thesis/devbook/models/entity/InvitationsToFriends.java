package com.pk.thesis.devbook.models.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
public class InvitationsToFriends implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne( )
    @JoinColumn(name="from_user_fk")
    private User from;

    @ManyToOne()
    @JoinColumn(name="to_user_fk")
    private User to;

    @Column(name = "invitation_date")
    private Date date;

    public InvitationsToFriends(User from, User to, Date start) {
        this.from = from;
        this.to = to;
        this.date = start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvitationsToFriends )) return false;
        return id != null && id.equals(((InvitationsToFriends) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getInvitationDate() {
        return date;
    }

    public void setInvitationDate(Date invitationDate) {
        this.date = invitationDate;
    }
}
