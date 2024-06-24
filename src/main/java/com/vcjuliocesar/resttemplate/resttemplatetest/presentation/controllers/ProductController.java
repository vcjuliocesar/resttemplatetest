package com.vcjuliocesar.resttemplate.resttemplatetest.presentation.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.vcjuliocesar.resttemplate.resttemplatetest.application.services.ProductService;
import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.CreateProductDto;
import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.ProductDto;
import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.CustomException;
//import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.ProductDeleteException;
import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.ProductUpdateException;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        try {
            return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);   
        } catch (Exception ex) {
            throw new CustomException("Failed to found products " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
        }catch(Exception ex){
            throw new CustomException("Failed to found product with ID " + id + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
       
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody CreateProductDto product) {
        try {
            productService.saveProduct(product);
        } catch (Exception ex) {
            throw new CustomException("Failed to saved product with Title " + product.getTitle() + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
       
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("id") Integer id,@RequestBody ProductDto product) {
        try{
            productService.updateProduct(id, product);
        }catch(Exception ex){
            throw new ProductUpdateException("Failed to update product with ID " + id + ": " + ex.getMessage());
        }
        
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Integer id) {
        try{
            productService.deleteProduct(id);
        }catch(Exception ex){
            throw new CustomException("Failed to delete product with ID " + id + ": " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
            //throw new ProductDeleteException("Failed to delete product with ID " + id + ": " + ex.getMessage());
        }
    }
}
