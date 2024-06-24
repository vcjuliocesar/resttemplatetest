package com.vcjuliocesar.resttemplate.resttemplatetest.presentation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vcjuliocesar.resttemplate.resttemplatetest.application.services.CategoryService;
import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.CreateCategoryDto;
import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.CategoryDto;
import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.CustomException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
     private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        try {
            return new ResponseEntity<>(categoryService.getCategories(),HttpStatus.OK);   
        } catch (Exception ex) {
            throw new CustomException("Failed to found categories " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getcategoryById(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity<>(categoryService.getById(id),HttpStatus.OK);
        }catch(Exception ex){
            throw new CustomException("Failed to found category with ID " + id + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
       
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody CreateCategoryDto category) {
        try {
            categoryService.saveCategory(category);
        } catch (Exception ex) {
            throw new CustomException("Failed to saved category with Name " + category.getName() + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
       
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable("id") Integer id,@RequestBody CategoryDto category) {
        try{
            categoryService.updateCategory(id, category);
        }catch(Exception ex){
            throw new CustomException("Failed to update category with ID " + id + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Integer id) {
        try{
            categoryService.deleteCategory(id);
        }catch(Exception ex){
            throw new CustomException("Failed to delete category with ID " + id + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
            //throw new categoryDeleteException("Failed to delete category with ID " + id + ": " + ex.getMessage());
        }
    }
}
