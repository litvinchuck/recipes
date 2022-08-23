package com.elitvinchuck.recipes.controllers;

import com.elitvinchuck.recipes.exceptions.RecipeNotFoundException;
import com.elitvinchuck.recipes.models.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class RecipeController {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        if ((id - 1) < recipes.size()) {
            return recipes.get(id - 1);
        }
        throw new RecipeNotFoundException("Recipe not found for id=" + id);
    }

    @PostMapping("/recipe/new")
    public Map<String, Integer> postNewRecipe(@RequestBody Recipe recipe) {
        recipes.add(recipe);
        return Map.of("id", recipes.size());
    }

}
