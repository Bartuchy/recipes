package com.markiewicz.recipes;

import com.markiewicz.recipes.recipe.Recipe;
import com.markiewicz.recipes.recipe.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication//(exclude = { DataSourceAutoConfiguration.class })
public class RecipesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}


//	@Bean
//	CommandLineRunner runner(RecipeRepository repository, MongoTemplate mongoTemplate) {
//		return args -> {
//			Recipe recipe = new Recipe(
//					LocalDateTime.now(),
//					"category1",
//					"some description",
//					List.of("ingredient1", "ingredient2"),
//					List.of("direction1", "direction2")
//			);
//			repository.insert(recipe);
//		};
//	}

}
