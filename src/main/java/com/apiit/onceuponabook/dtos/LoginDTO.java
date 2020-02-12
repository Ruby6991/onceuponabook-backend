package com.apiit.onceuponabook.dtos;

import com.apiit.onceuponabook.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private int id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    private UserDTO user;

    private String jwtToken;

    public LoginDTO(String jwtToken,UserRole role,UserDTO user){
        this.jwtToken=jwtToken;
        this.role=role;
        this.user=user;
    }
}
