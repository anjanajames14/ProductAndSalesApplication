package com.product.productandsales.controller;

import com.product.productandsales.dto.SalesDto;
import com.product.productandsales.entity.Sale;
import com.product.productandsales.impl.SalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    SalesServiceImpl salesService;

    @GetMapping
    public List<SalesDto> getAllSales(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return salesService.getAllSales(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesDto> getSaleById(@PathVariable int id) {
        return ResponseEntity.ok(salesService.getSaleById(id));
    }

    @PostMapping
    public ResponseEntity<Sale> addSale(@RequestParam int productId, @RequestParam int quantity) {
        Sale newSale = salesService.addSale(productId, quantity);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSale(@PathVariable int id, @RequestBody Sale sale) {
        salesService.updateSales(id, sale);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
        salesService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
