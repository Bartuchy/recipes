package com.markiewicz.recipes.recipe;

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
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
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
    public ResponseEntity<?> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeRecipeById(@PathVariable Long id) {
        recipeService.removeRecipeById(id);
        return ResponseEntity.noContent().build();
    }
}
