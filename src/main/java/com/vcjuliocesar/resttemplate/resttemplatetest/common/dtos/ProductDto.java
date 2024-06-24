package com.vcjuliocesar.resttemplate.resttemplatetest.common.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String title;
    private Integer price;
    private String description;
    private CategoryDto category;
    private String[] images;
}
