package com.markiewicz.recipes.recipe.dto;


import com.markiewicz.recipes.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class RecipeDto {
    private Long id;
    private LocalDateTime additionDate;
    private String name;
    private String category;
    private String description;
    private String ingredients;
    private String directions;
    private String userName;

    public RecipeDto(Recipe recipe) {
        this.id = recipe.getId();
        this.additionDate = recipe.getDate();
        this.name = recipe.getName();
        this.category = recipe.getCategory();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.directions = recipe.getDirections();
        this.userName = recipe.getUser().getUsername();
    }
}
