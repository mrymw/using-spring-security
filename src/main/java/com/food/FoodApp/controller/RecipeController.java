package com.food.FoodApp.controller;

import com.food.FoodApp.model.Recipe;
import com.food.FoodApp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class RecipeController {
    private RecipeService recipeService;
    @Autowired
    public void setRecipeService(RecipeService recipeService){
        this.recipeService = recipeService;
    }
    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createRecipes(
            @PathVariable (value = "categoryId") Long categoryId,
            @RequestBody Recipe recipeObject) {
        return recipeService.createRecipes(categoryId, recipeObject);
    }
    @GetMapping("/categories/{categoryId}/recipes")
    public List<Recipe> getRecipe(@PathVariable(value = "categoryId") Long categoryId) {
        return recipeService.getRecipe(categoryId);
    }
    @GetMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe getRecipe(@PathVariable(value = "categoryId") Long categoryId,
                                  @PathVariable(value = "recipeId") Long recipeId) {
        return recipeService.getRecipe(categoryId, recipeId);
    }
    @GetMapping("/categories/{categoryId}/recipes/delete/{recipeId}")
    public Optional<Recipe> deleteRecipe(@PathVariable(value = "categoryId") Long categoryId,
                              @PathVariable(value = "recipeId") Long recipeId) {
        return recipeService.deleteRecipe(categoryId, recipeId);
    }
    @PutMapping("/categories/{categoryId}/recipes/update/{recipeId}")
    public Optional<Recipe> updateRecipe(@PathVariable(value = "categoryId") Long categoryId,
                                         @PathVariable(value = "recipeId") Long recipeId, @RequestBody Recipe updateRecipe) {
        return recipeService.updateRecipe(categoryId, recipeId, updateRecipe);
    }
//    @PutMapping("/categories/{categoryId}/recipes/portions/{portions}")
//    public List<Recipe> findByPortions (@PathVariable(value = "categoryId") Long categoryId,
//                                        @PathVariable(value = "portions") Integer portions) {
//        return recipeService.findByPortions(categoryId, portions);
//    }
//    @PutMapping("/categories/{categoryId}/recipes/orderby/{recipeId}")
//    public List<Recipe> alphabeticalOrderBy (@PathVariable(value = "categoryId") Long categoryId,
//                                        @PathVariable(value = "recipeId") Long recipeId) {
//        return recipeService.alphabetical(categoryId);
//    }

}