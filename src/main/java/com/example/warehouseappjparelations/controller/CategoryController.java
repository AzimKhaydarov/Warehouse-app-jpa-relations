package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Category;
import com.example.warehouseappjparelations.payload.CategoryDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return result;
    }
    @PutMapping("/{id}")
    public Result editCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        Result result = categoryService.editCategory(categoryDto, id);
        return result;
    }
    @GetMapping
    public List<Category> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return categories;
    }
    @GetMapping("/{id}")
    public Object getCategory(@PathVariable Integer id){
        Object result = categoryService.getCategory(id);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
        return result;
    }

}
