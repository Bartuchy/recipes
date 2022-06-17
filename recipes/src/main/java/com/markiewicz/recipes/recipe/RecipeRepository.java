package com.markiewicz.recipes.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    @Query("select r from Recipe r where lower(r.name) like lower(concat('%',:name, '%')) order by r.date desc")
    Optional<List<Recipe>> findByNameContainingIgnoreCaseOrderByDateDesc(String name);

    @Query("select r from Recipe r where lower(r.category) like lower(concat('%',:category, '%')) order by r.date desc")
    Optional<List<Recipe>> findByCategoryContainingIgnoreCaseOrderByDateDesc(String category);

    @Query("select r from Recipe r where :username=r.user.username")
    Optional<List<Recipe>> findRecipesAddedByUser(String username);
}
