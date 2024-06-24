package com.vcjuliocesar.resttemplate.resttemplatetest.application.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.CategoryDto;
import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.CreateCategoryDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryService {

    @Value("${spring.external.service.base-url}")
    private String basePath;

    private final RestTemplate restTemplate;

     public List<CategoryDto> getCategories(){
        CategoryDto[] response = restTemplate.getForObject(basePath+"categories", CategoryDto[].class);
        return Arrays.asList(response);
    }

    public CategoryDto getById(Integer id){
        CategoryDto response = restTemplate.getForObject(basePath+"categories/"+id,CategoryDto.class);
        return response;
    }

    public void saveCategory(CreateCategoryDto category){
        restTemplate.postForObject(basePath+"categories",category, CreateCategoryDto.class);
    }

    public void updateCategory(Integer id,CategoryDto category){
        restTemplate.put(basePath+"categories/"+id, category);
    }

    public void deleteCategory(Integer id){
        restTemplate.delete(basePath+"categories/"+id);
    }
}