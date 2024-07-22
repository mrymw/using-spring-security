package com.food.FoodApp.service;

import com.food.FoodApp.exception.InformationNotFoundException;
import com.food.FoodApp.model.Category;
import com.food.FoodApp.model.Recipe;
import com.food.FoodApp.repository.CategoryRepository;
import com.food.FoodApp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RecipeService {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Recipe createRecipes(Long categoryID, Recipe recipeObject) {
        try {
            Optional category = categoryRepository.findById(categoryID);
            recipeObject.setCategory((Category) category.get());
            return recipeRepository.save(recipeObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("NO SUCH ID FOUND!!! ");
        }
    }
    public List<Recipe> getRecipe(Long categoryID) {
        Optional<Category> category = categoryRepository.findById(categoryID);
        if(category.isPresent()) {
            return category.get().getRecipesList();
        } else {
            throw  new InformationNotFoundException("DOES NOT EXIST!!");
        }
    }
    public Recipe getRecipe(Long categoryID, Long recipeID) {
        Optional<Category> category= categoryRepository.findById(categoryID);
        if(category.isPresent()) {

            Optional<Recipe> recipe = recipeRepository.findByCategoryId(categoryID).stream().filter(i -> i.getId().equals(recipeID)).findFirst();
            if (recipe.isEmpty()) {
                throw new InformationNotFoundException("RECIPE ID NOT FOUND");
            } else {
                return recipe.get();
            }
        } else {
            throw new InformationNotFoundException("CATEGORY ID DOES NOT EXIST");
        }
    }
    public Optional<Recipe> deleteRecipe(Long categoryID, Long recipeID) {
        Optional<Category> category = categoryRepository.findById(categoryID);
        if (category.isPresent()) {
            Optional<Recipe> recipe = recipeRepository.findByCategoryId(categoryID).stream().filter(i-> i.getId().equals(recipeID)).findFirst();
            if (recipe.isPresent()) {
                recipeRepository.deleteById(recipeID);
                return recipe;
            } else {
                throw new InformationNotFoundException("RECIPE DOES NOT EXIST");
            }
        } else {
            throw new InformationNotFoundException("Category with ID: " + categoryID + "not found");
        }
    }
    public Optional<Recipe> updateRecipe (Long categoryID, Long recipeID, Recipe updateRecipe) {
        Optional<Category> category = categoryRepository.findById(categoryID);
        if (category.isPresent()) {
            Optional<Recipe> recipe = recipeRepository.findByCategoryId(categoryID).stream().filter(i-> i.getId().equals(recipeID)).findFirst();
            if (recipe.isPresent()) {
                Recipe update = recipe.get();
                update.setName(updateRecipe.getName());
                update.setTime(updateRecipe.getTime());
                update.setPortions(updateRecipe.getPortions());
                update.setIngredients(updateRecipe.getIngredients());
                update.setSteps(updateRecipe.getSteps());
                update.setIsPublic(updateRecipe.getIsPublic());
                return Optional.of(recipeRepository.save(update));
            } else {
                throw new InformationNotFoundException("RECIPE NOT FOUND!!");
            }
        } else {
            throw new InformationNotFoundException("Category with ID: " + categoryID + "not found");
        }
    }
//    public List<Recipe> findByPortions(Long categoryID, Integer portions) {
//        Optional<Category> category = categoryRepository.findById(categoryID);
//        if (category.isPresent()) {
//            List<Recipe> portion = recipeRepository.findAll();
//            return portion;
//        } else {
//            throw new InformationNotFoundException("RECIPE NOT FOUND!!");
//        }
//    }
//    public List<Recipe> alphabetical(Long categoryId) {
//        Optional<Category> category = categoryRepository.findById(categoryId);
//        if (category.isPresent()) {
//            return recipeRepository.findByOrderByRecipeAsc();
//        } else {
//            throw new InformationNotFoundException("RECIPE NOT FOUND!!");
//        }
//    }
}