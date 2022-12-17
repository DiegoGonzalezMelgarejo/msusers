package com.msusers.diego.dto;



import lombok.Data;


import java.time.LocalDate;
import java.util.List;


@Data
public class UserDto {
    private String id;

    private String name;



    private String email;

    private String password;

    private List<PhoneDto> phones;

    private LocalDate created;


    private LocalDate lastLogin;


    private boolean isActive;
    private String token;
}
