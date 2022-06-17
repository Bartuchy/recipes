package com.markiewicz.recipes.exception;

import com.markiewicz.recipes.user.User;
import com.markiewicz.recipes.user.dto.UserRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException{

    public UserExistsException(UserRegisterDto user) {
        super("User with this email or username already exists");
    }
}
