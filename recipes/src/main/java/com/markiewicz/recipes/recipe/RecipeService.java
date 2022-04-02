package com.markiewicz.recipes.recipe;

import com.markiewicz.recipes.exception.RecipeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {
    RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(String id) {
        return recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);
    }

    public Recipe addNewRecipe(Recipe recipe) {
        return recipeRepository.insert(recipe);
    }

    @Transactional
    public void updateRecipe(String id, Recipe recipe) {
        Recipe updatedRecipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        updateRecipeFields(updatedRecipe, recipe);
    }

    private void updateRecipeFields(Recipe updatedRecipe, Recipe recipe) {
        updatedRecipe.setName(recipe.getName());
        updatedRecipe.setCategory(recipe.getCategory());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setIngredients(recipe.getIngredients());
        updatedRecipe.setDirections(recipe.getDirections());
        updatedRecipe.setDate(LocalDateTime.now());
    }

    public void removeRecipeById(String id) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        recipeRepository.delete(recipe);
    }
}
