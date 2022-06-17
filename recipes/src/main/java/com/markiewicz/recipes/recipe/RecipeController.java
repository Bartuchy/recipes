package com.markiewicz.recipes.recipe;

import com.markiewicz.recipes.recipe.dto.RecipeDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/recipe")
public class RecipeController {
    RecipeService recipeService;

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        RecipeDto recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("profile/{username}")
    public ResponseEntity<List<RecipeDto>> getRecipesAddedByUser(@PathVariable String username) {
        List<RecipeDto> recipes = recipeService.getRecipesAddedByUser(username);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> getRecipesWithNameOrFromCategory(@Nullable @RequestParam String name, @Nullable @RequestParam String category) {
        List<Recipe> recipes = recipeService.getRecipesWithNameOrFromCategory(name, category);
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        Recipe addedRecipe = recipeService.addNewRecipe(recipe);
        return new ResponseEntity<>(addedRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeRecipeById(@PathVariable Long id) {
        recipeService.removeRecipeById(id);
        return ResponseEntity.noContent().build();
    }
}
