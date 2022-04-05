package com.markiewicz.recipes.user;

import com.markiewicz.recipes.recipe.Recipe;
import com.markiewicz.recipes.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;

    @Email
    private String email;

    @Pattern(regexp = ".{8,}")
    private String password;

    private List<Role> roles = new ArrayList<>();
    private List<Recipe> recipes = new ArrayList<>();
}
