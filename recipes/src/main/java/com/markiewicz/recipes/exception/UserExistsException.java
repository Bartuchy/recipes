package com.markiewicz.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException{
    public UserExistsException() {
        System.out.println("User with this email already exists");
    }
}
