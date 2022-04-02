package com.markiewicz.recipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Recipe {
    @Id
    private String id;
    private LocalDateTime date;
    private String name;
    private String category;
    private String description;
    private List<String> ingredients = new ArrayList<>();
    private List<String> directions = new ArrayList<>();

    public Recipe(LocalDateTime date, String category, String description, List<String> ingredients, List<String> directions) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}
