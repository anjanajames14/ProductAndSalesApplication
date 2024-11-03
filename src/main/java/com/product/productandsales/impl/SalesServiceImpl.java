package com.product.productandsales.impl;

import com.product.productandsales.dto.SalesDto;
import com.product.productandsales.entity.Product;
import com.product.productandsales.entity.Sale;
import com.product.productandsales.handler.ResourceNotFoundException;
import com.product.productandsales.repository.ProductRepository;
import com.product.productandsales.repository.SalesRepository;
import com.product.productandsales.service.SalesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SalesDto> getAllSales(int page, int size) {
        List<Sale> sales = salesRepository.findAll(PageRequest.of(page, size)).getContent();
        return sales.stream()
                .map(sale -> new SalesDto(sale)) // Use a lambda expression
                .collect(Collectors.toList());
}

    @Override
    public SalesDto getSaleById(int id) {
        Sale sale = salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales not found"));
        return new SalesDto(sale);
    }

    @Override
    public void updateSales(int id, Sale sale) {
        Sale existingSale = salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        existingSale.setQuantity(sale.getQuantity());
        existingSale.setSaleDate(sale.getSaleDate());

        Product updatedProduct = productRepository.findById(sale.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existingSale.setProduct(updatedProduct);

        salesRepository.save(existingSale);
    }

    @Override
    public void deleteSale(int id) {
        salesRepository.deleteById(id);
    }

}
