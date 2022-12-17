package com.msusers.diego.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

import static com.msusers.diego.utils.Constanst.REGEDEXEMAIL;

@Data
public class CreateUserDto {

    private  String name;
    @Pattern(regexp = REGEDEXEMAIL,  message = "No es un formato valido para un email")
    private String email;
    private  String password;

    private List<PhoneDto> phones;
}
