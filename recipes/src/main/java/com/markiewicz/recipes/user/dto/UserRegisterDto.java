package com.markiewicz.recipes.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterDto {
    @Email
    private String email;

    @Pattern(regexp = "[a-zA-z]{3,20}[0-9]*")
    private String username;

    @Pattern(regexp = "[a-zA-Z]{4,25}[0-9]+")
    private String password;
}
