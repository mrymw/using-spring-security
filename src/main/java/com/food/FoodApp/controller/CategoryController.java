package com.food.FoodApp.controller;

import com.food.FoodApp.model.Category;
import com.food.FoodApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired // creates class on its own
    public void setCategoryRepository(CategoryService categoryService){
        this.categoryService= categoryService;
    }
    //CRUD Operations
    //CRUD - C - HTTP POST - To create a record
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("Calliing create category");
        return categoryService.createCategory(categoryObject);
    }
    @GetMapping("/categories")
    public List<Category> getCategories(){
        System.out.println("Calling getCategories");
        return categoryService.getCategories();
    }
    @GetMapping("/categories/{categoryID}")
    public Optional<Category> getCategory(@PathVariable Long categoryID){
        System.out.println("Calling getCategory");
        return categoryService.getCategory(categoryID);
    }
    @GetMapping("/categories/delete/{categoryID}")
    public Optional<Category> deleteCategory(@PathVariable Long categoryID){
        System.out.println("Calling deleteCategory");
        return categoryService.deleteCategory(categoryID);
    }
    @GetMapping("/categories/update/{categoryID}")
    public Optional<Category> updateCategory(@PathVariable Long categoryID, @RequestBody Category categoryObject){
        System.out.println("Calling updateCategory");
        return categoryService.updateCategory(categoryID, categoryObject);
    }

    //CRUD - R - HTTP GET - TO read a record
    //CRUD - U - HTTP PUT/POST - TO UPDATE A RECORD
    //CRUD - D - HTTP DELETE - TO DELETE A RECORD



}

