package com.product.productandsales.service;

import com.product.productandsales.dto.SalesDto;
import com.product.productandsales.entity.Sale;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SalesService {
    @Transactional
    Sale addSale(int productId, int quantity);

    List<SalesDto> getAllSales(int page, int size);

    SalesDto getSaleById(int id);

    void updateSales(int id, Sale sale);

    void deleteSale(int id);
}
