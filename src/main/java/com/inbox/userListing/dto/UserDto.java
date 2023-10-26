package com.inbox.userListing.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    private String name;
    private String email;
     private String password;
    private String address;
    private String city;
}
