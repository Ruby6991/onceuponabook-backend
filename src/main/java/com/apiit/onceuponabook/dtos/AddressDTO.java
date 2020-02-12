package com.apiit.onceuponabook.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private int id;
    private String country;
    private String address;
    private String city;
    private String postalCode;
    private UserDTO user;
    private List<OrderDTO> orders;
}
