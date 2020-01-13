package com.pk.thesis.devbook.controller.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;
}
