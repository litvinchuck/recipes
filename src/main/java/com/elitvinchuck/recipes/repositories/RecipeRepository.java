package com.elitvinchuck.recipes.repositories;

import com.elitvinchuck.recipes.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {

    Page<Recipe> findByCategory(String category, Pageable pageable);

}
