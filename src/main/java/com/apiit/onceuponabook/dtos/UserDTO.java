package com.apiit.onceuponabook.dtos;

import com.apiit.onceuponabook.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String fullName;
    private int phoneNo;
    private String password;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LoginDTO login;
    private List<AddressDTO> addresses;
    private List<OrderDTO> orders;
    private List<BookDTO> books;
    private List<RatingDTO> ratings;

    public UserDTO(String email, String fullName, int phoneNo) {
        this.email=email;
        this.fullName=fullName;
        this.phoneNo=phoneNo;
    }
}
