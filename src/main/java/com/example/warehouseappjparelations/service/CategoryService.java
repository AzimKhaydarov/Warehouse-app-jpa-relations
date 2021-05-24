package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Category;
import com.example.warehouseappjparelations.payload.CategoryDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) return new Result("Parent category doesn't exist!", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        category.setActive(categoryDto.isActive());
        Category savedCategory = categoryRepository.save(category);

        return new Result("Category added successfully!", true);
    }

    public Result editCategory(CategoryDto categoryDto, Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("The chosen category not found!", false);
        Category selectedCategory = optionalCategory.get();
        selectedCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) return new Result("Parent category doesn't exist!", false);
            selectedCategory.setParentCategory(optionalParentCategory.get());
        }
        selectedCategory.setActive(categoryDto.isActive());
        Category editedCategory = categoryRepository.save(selectedCategory);
        return new Result("Selected category edited successfully!", true);
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    public Object getCategory( Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()) return new Result("The category with current id not found!", false);
        return optionalCategory.get();
    }
    public Result deleteCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent()) return new Result("The category with current id not found!", false);
        categoryRepository.delete(optionalCategory.get());
       return new Result("Chosen category deleted successfully!", true);
    }
}
