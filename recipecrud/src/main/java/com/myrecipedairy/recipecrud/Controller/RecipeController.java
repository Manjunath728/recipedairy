package com.myrecipedairy.recipecrud.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myrecipedairy.recipecrud.Entity.Recipe;
import com.myrecipedairy.recipecrud.Repository.RecipeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/")
    public List<Recipe> getAllPublicRecipes() {
        return recipeRepository.findByAccesstype("public");
    }
    @PostMapping("/")
    public ResponseEntity<?> createRecipe(@Valid @RequestBody Recipe recipe,
    BindingResult result) {
    List<String> displayErrors = new ArrayList<String>();
    if (result.hasErrors()) {
    List<FieldError> errors = result.getFieldErrors();
    for(FieldError err:errors) {
    displayErrors.add(err.getField() + ": " + err.getDefaultMessage());
    System.out.println(displayErrors);
    }
    return ResponseEntity.badRequest().body(displayErrors);
    }
    recipeRepository.save(recipe);
    return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable("id") Long id) {
        return recipeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@Valid @PathVariable("id") Long id, @Valid @RequestBody Recipe newRecipe, BindingResult result) {
        List<String> displayErrors = new ArrayList<>();
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError err:errors) {
                displayErrors.add(err.getField() + ": " + err.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(displayErrors);
        }
        
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setRecipename(newRecipe.getRecipename());
                    recipe.setCategory(newRecipe.getCategory());
                    recipe.setAccesstype(newRecipe.getAccesstype());
                    // Update other fields as needed
                    Recipe updatedRecipe = recipeRepository.save(recipe);
                    return ResponseEntity.ok(updatedRecipe);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }
}
