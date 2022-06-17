package com.markiewicz.recipes.recipe;

import com.markiewicz.recipes.exception.ForbiddenException;
import com.markiewicz.recipes.exception.RecipeNotFoundException;

import com.markiewicz.recipes.recipe.dto.RecipeDto;
import com.markiewicz.recipes.user.UserService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    UserService userService;

    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(RecipeDto::new).toList();
    }

    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);
        return new RecipeDto(recipe);
    }

    public List<RecipeDto> getRecipesAddedByUser(String username) {
        return recipeRepository
                .findRecipesAddedByUser(username)
                .orElseThrow(RecipeNotFoundException::new)
                .stream()
                .map(RecipeDto::new)
                .toList();

    }

    public List<Recipe> getRecipesWithNameOrFromCategory(String name, String category) {
        if (name == null ^ category == null) {
            if (name != null) {
                return recipeRepository
                        .findByNameContainingIgnoreCaseOrderByDateDesc(name)
                        .orElseThrow(RecipeNotFoundException::new);
            }
            return recipeRepository
                    .findByCategoryContainingIgnoreCaseOrderByDateDesc(category)
                    .orElseThrow(RecipeNotFoundException::new);
        }
        throw new RecipeNotFoundException();
    }

    public Recipe addNewRecipe(Recipe recipe) {
        recipe.setDate(LocalDateTime.now());
        recipe.setUser(userService.getLoggedInUser());
        return recipeRepository.save(recipe);
    }

    @Transactional
    public void updateRecipe(Long id, Recipe recipe) {
        Recipe updatedRecipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if(checkCompatibility(updatedRecipe)) {
            updateRecipeFields(updatedRecipe, recipe);
        } else {
            throw new ForbiddenException();
        }
    }

    private void updateRecipeFields(Recipe updatedRecipe, Recipe recipe) {
        updatedRecipe.setName(recipe.getName());
        updatedRecipe.setCategory(recipe.getCategory());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setIngredients(recipe.getIngredients());
        updatedRecipe.setDirections(recipe.getDirections());
        updatedRecipe.setDate(LocalDateTime.now());
    }

    public void removeRecipeById(Long id) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (checkCompatibility(recipe)) {
            recipeRepository.delete(recipe);
        } else {
            throw new ForbiddenException();
        }
    }

    private boolean checkCompatibility(Recipe recipe) {
        Long loggedInUserId = userService.getLoggedInUser().getId();
        Long recipeAuthorId = recipe.getUser().getId();
        return Objects.equals(loggedInUserId, recipeAuthorId);
    }
}
