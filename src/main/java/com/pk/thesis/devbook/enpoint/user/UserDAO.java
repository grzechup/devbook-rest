package com.pk.thesis.devbook.enpoint.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class UserDAO {

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
    private String firstname;

    @Column
    private String lastname;

    @Column(name= "date_of_birth")
    private Date dateOfBirth;

    @Column
    private Integer role;
}
