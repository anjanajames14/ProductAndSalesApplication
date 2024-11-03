package com.product.productandsales.repository;

import com.product.productandsales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale,Integer> {

}
