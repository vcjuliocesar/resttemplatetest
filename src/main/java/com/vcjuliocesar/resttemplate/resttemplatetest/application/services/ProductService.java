package com.vcjuliocesar.resttemplate.resttemplatetest.application.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.CreateProductDto;
import com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos.ProductDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${spring.external.service.base-url}")
    private String basePath;

    private final RestTemplate restTemplate;

    public List<ProductDto> getProducts(){
        ProductDto[] response = restTemplate.getForObject(basePath+"products", ProductDto[].class);
        return Arrays.asList(response);
    }

    public ProductDto getById(Integer id){
        ProductDto response = restTemplate.getForObject(basePath+"products/"+id,ProductDto.class);
        return response;
    }

    public void saveProduct(CreateProductDto product){
        restTemplate.postForObject(basePath+"products",product, CreateProductDto.class);
    }

    public void updateProduct(Integer id,ProductDto product){
        restTemplate.put(basePath+"products/"+id, product);
    }

    public void deleteProduct(Integer id){
        restTemplate.delete(basePath+"products/"+id);
    }
}
