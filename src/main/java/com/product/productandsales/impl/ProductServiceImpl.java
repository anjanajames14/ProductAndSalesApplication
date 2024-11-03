package com.product.productandsales.impl;

import com.product.productandsales.entity.Product;
import com.product.productandsales.handler.ResourceNotFoundException;
import com.product.productandsales.repository.ProductRepository;
import com.product.productandsales.repository.SalesRepository;
import com.product.productandsales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Override
    public List<Product> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(int id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        productRepository.save(existingProduct);
    }
    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }


    public double getTotalRevenue() {
        return salesRepository.findAll().stream()
                .mapToDouble(sale -> {
                    Product product = sale.getProduct();
                    return (product != null) ? product.getPrice() * sale.getQuantity() : 0;
                })
                .sum();
    }

    public double getRevenueByProduct(int productId) {
        return salesRepository.findAll().stream()
                .filter(sale -> {
                    Integer saleProductId = sale.getProduct().getId();
                    return saleProductId.equals(productId);
                })
                .mapToDouble(sale -> {
                    Product product = sale.getProduct(); // Directly get the Product from the Sale
                    return (product != null) ? product.getPrice() * sale.getQuantity() : 0;
                })
                .sum();
    }
}
