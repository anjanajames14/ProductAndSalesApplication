package com.product.productandsales.service;

import com.product.productandsales.entity.Sale;
import jakarta.transaction.Transactional;

public interface SalesService {
    @Transactional
    Sale addSale(int productId, int quantity);
}
