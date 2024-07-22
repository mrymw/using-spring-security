package com.food.FoodApp.service;

import com.food.FoodApp.exception.InformationNotFoundException;
import com.food.FoodApp.model.Category;
import com.food.FoodApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired // creates class on its own
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository= categoryRepository;
    }
    public Category createCategory(Category categoryObject) {
        System.out.println("Service Calling create category");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationNotFoundException("Category with name: " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }
    public List<Category> getCategories() {
        System.out.println("Service Calling getCategrories: ");
        return categoryRepository.findAll();
    }
    public Optional<Category>getCategory(Long categoryID) {
        System.out.println("Service Calling getCategory: ");
        Optional<Category> category = categoryRepository.findById(categoryID);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("Category with ID: " + categoryID + "not found");
        }
    }
    public Optional<Category> deleteCategory(Long categoryID) {
        System.out.println("Service Calling deleteID: ");
        Optional<Category> category = categoryRepository.findById(categoryID);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryID);
            return category;
        } else {
            throw new InformationNotFoundException("Category with ID: " + categoryID + "not found");
        }
    }
    public Optional<Category> updateCategory (Long categoryID, Category updateCategory) {
        System.out.println("Service calling updateID :");
        Optional<Category> category = categoryRepository.findById(categoryID);
        if (category.isPresent()) {
            Category update = category.get();
            update.setName(updateCategory.getName());
            update.setDescription(updateCategory.getDescription());
            return Optional.of(categoryRepository.save(update));
        } else {
            throw new InformationNotFoundException("Category with ID: " + categoryID + "not found");
        }
    }


}
