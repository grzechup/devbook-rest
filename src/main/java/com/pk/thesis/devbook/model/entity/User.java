package com.pk.thesis.devbook.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String lastName;
    private Date dateOfBirth;
    private String role;
    
    
}
