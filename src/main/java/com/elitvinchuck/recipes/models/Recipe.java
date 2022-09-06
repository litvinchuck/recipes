package com.elitvinchuck.recipes.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private Set<String> ingredients = new HashSet<>();

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "recipe_directions",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private Set<String> directions = new HashSet<>();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public void setDateToNow() {
        date = LocalDate.now();
    }

}
