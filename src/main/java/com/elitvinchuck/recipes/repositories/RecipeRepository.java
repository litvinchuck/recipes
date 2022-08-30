package com.elitvinchuck.recipes.repositories;

import com.elitvinchuck.recipes.models.Recipe;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {
}
