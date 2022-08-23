package com.elitvinchuck.recipes.controllers;

import com.elitvinchuck.recipes.exceptions.RecipeNotFoundException;
import com.elitvinchuck.recipes.models.Recipe;
import com.elitvinchuck.recipes.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("recipe")
public class RecipeController {

    private RecipeRepository repository;

    @Autowired
    public RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        Optional<Recipe> recipeOptional = repository.findById(id);
        if (recipeOptional.isPresent()) {
            return recipeOptional.get();
        }
        throw new RecipeNotFoundException("Recipe not found for id=" + id);
    }

    @PostMapping("/new")
    public Map<String, Long> postNewRecipe(@Valid @RequestBody Recipe recipe) {
        long id = repository
                .save(recipe)
                .getId();
        return Map.of("id", id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        repository.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
