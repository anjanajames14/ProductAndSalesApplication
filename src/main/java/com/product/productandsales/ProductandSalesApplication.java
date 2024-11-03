package com.product.productandsales;

import com.product.productandsales.entity.Product;
import com.product.productandsales.service.ProductService;
import com.product.productandsales.service.SalesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductandSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductandSalesApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductService productService, SalesService saleService) {
		return args -> {
			Product product1 = new Product("Laptop", "High-end gaming laptop", 1200.00, 10);
			Product product2 = new Product("Smartphone", "Latest model smartphone", 800.00, 20);
			Product product3 = new Product("Headphones", "Noise-cancelling headphones", 150.00, 30);

			productService.addProduct(product1);
			productService.addProduct(product2);
			productService.addProduct(product3);

			saleService.addSale(product1.getId(), 1);
			saleService.addSale(product2.getId(), 2);
			saleService.addSale(product3.getId(), 3);
		};
	}

}



