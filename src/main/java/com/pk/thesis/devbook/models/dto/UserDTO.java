package com.pk.thesis.devbook.models.dto;

import com.pk.thesis.devbook.models.ERole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class UserDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Long experience;
    private Set<ERole> roles;
}
