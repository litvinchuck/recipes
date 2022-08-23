package com.elitvinchuck.recipes.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Recipe {

    private String name;

    private String description;

    private ArrayList<String> ingredients;

    private ArrayList<String> directions;

}
