package com.markiewicz.recipes.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.markiewicz.recipes.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.OnDeleteAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private String name;
    private String category;
    private String description;

//    @ElementCollection
//    @CollectionTable(name = "ingredient", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "name")
    private String ingredients;

//    @ElementCollection
//    @CollectionTable(name = "direction", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "name")
    private String directions;

    @ManyToOne
    @JsonIgnoreProperties(value="recipes")
    private User user;

    public Recipe(String name, String category, String description, String ingredients, String directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}
