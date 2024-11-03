package com.product.productandsales.impl;

import com.product.productandsales.entity.Product;
import com.product.productandsales.entity.Sale;
import com.product.productandsales.handler.ResourceNotFoundException;
import com.product.productandsales.repository.ProductRepository;
import com.product.productandsales.repository.SalesRepository;
import com.product.productandsales.service.SalesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Sale addSale(int productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Sale sale = new Sale(product, quantity, new Date(System.currentTimeMillis()));
        return salesRepository.save(sale);
    }
}
