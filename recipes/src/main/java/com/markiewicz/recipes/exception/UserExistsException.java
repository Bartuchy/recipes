package com.markiewicz.recipes.exception;

import com.markiewicz.recipes.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException{
//    public UserExistsException() {
//        System.out.println("User with this email already exists");
//    }

    public UserExistsException(User user) {
        super("User with email " + user.getEmail() + " already exists");
    }
}
