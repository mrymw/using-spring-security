package com.food.FoodApp.repository;

import com.food.FoodApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
