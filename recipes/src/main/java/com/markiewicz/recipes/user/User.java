package com.markiewicz.recipes.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.markiewicz.recipes.recipe.Recipe;
import com.markiewicz.recipes.role.Role;
import com.markiewicz.recipes.user.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Email
    private String email;

    //@Pattern(regexp = "[a-zA-z]{3,}.*")
    private String username;

    //@Pattern(regexp = "[a-zA-Z]{4,}[0-9]+")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value="user")
    private List<Recipe> recipes = new ArrayList<>();

    public User(UserRegisterDto userRegisterDto, String password) {
        this.email = userRegisterDto.getEmail();
        this.username = userRegisterDto.getUsername();
        this.password = password;
    }
}
