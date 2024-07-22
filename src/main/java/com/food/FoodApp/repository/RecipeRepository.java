package com.food.FoodApp.repository;

import com.food.FoodApp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String recipeName);
    List<Recipe> findByCategoryId(Long categoryID);
    //List<Recipe> findByPortions(Integer portions);
    //List<Recipe> findByOrderByRecipeAsc();


}
