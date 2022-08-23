package com.elitvinchuck.recipes.repositories;

import com.elitvinchuck.recipes.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
