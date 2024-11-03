package com.product.productandsales.dto;

import com.product.productandsales.entity.Sale;
import lombok.Data;

import java.sql.Date;

@Data
public class SalesDto {
    private Integer id;
    private int quantity;
    private Date saleDate;
    private ProductDto product;

    public SalesDto(Sale sale) {
        this.id = sale.getId();
        this.quantity = sale.getQuantity();
        this.saleDate = sale.getSaleDate();
        if (sale.getProduct() != null) {
            this.product = new ProductDto(sale.getProduct());
        }
    }
}