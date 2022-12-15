package com.msusers.diego.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
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
