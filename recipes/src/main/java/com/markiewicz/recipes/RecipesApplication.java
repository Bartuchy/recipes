package com.markiewicz.recipes;

import com.markiewicz.recipes.recipe.Recipe;
import com.markiewicz.recipes.recipe.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RecipeRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Recipe recipe = new Recipe(
					LocalDateTime.now(),
					"category1",
					"some description",
					List.of("ingredient1", "ingredient2"),
					List.of("direction1", "direction2")
			);
			repository.insert(recipe);
		};
	}

}
