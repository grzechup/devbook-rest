package com.pk.thesis.devbook.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Photo {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String photoPath;
    
}
