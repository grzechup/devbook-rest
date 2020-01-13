package com.pk.thesis.devbook.entity.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoleDAO {

    @Id
    private Long id;

    @Column
    private String name;

}
