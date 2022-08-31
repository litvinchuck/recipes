package com.elitvinchuck.recipes.controllers;

import com.elitvinchuck.recipes.constants.RecipeConstants;
import com.elitvinchuck.recipes.exceptions.RecipeNotFoundException;
import com.elitvinchuck.recipes.models.Recipe;
import com.elitvinchuck.recipes.repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("recipe")
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeRepository repository;

    @Autowired
    public RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        Optional<Recipe> recipeOptional = repository.findById(id);
        if (recipeOptional.isPresent()) {
            LOGGER.info(RecipeConstants.GET_RECIPE_SUCCESS_STRING + recipeOptional.get());
            return recipeOptional.get();
        }
        LOGGER.error("GET: " + RecipeConstants.RECIPE_NOT_FOUND_FOR_ID_STRING + id);
        throw new RecipeNotFoundException(RecipeConstants.RECIPE_NOT_FOUND_FOR_ID_STRING + id);
    }

    @GetMapping("/page")
    public Page<Recipe> getRecipePaginated(
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        LOGGER.info(RecipeConstants.GET_PAGING_SUCCESS_STRING + pageable);
        return repository.findAll(pageable);
    }

    @PostMapping("/new")
    public Map<String, Long> postNewRecipe(@Valid @RequestBody Recipe recipe) {
        long id = repository
                .save(recipe)
                .getId();
        LOGGER.info(RecipeConstants.POST_RECIPE_SUCCESS_STRING + recipe);
        return Map.of("id", id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            LOGGER.info(RecipeConstants.DELETE_RECIPE_SUCCESS_STRING + id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
        LOGGER.error("DELETE: " + RecipeConstants.RECIPE_NOT_FOUND_FOR_ID_STRING + id);
        throw new RecipeNotFoundException(RecipeConstants.RECIPE_NOT_FOUND_FOR_ID_STRING + id);
    }

}
