package com.product.productandsales.dto;

import com.product.productandsales.entity.Product;
import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String name;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }
}
