package com.product.productandsales.controller;

import com.product.productandsales.entity.Product;
import com.product.productandsales.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/revenue")
    public ResponseEntity<Double> getTotalRevenue() {
        double revenue = productService.getTotalRevenue();
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }

    @GetMapping("/revenue/{productId}")
    public ResponseEntity<Double> getRevenueByProduct(@PathVariable Integer productId) {
        double revenue = productService.getRevenueByProduct(productId);
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }
}