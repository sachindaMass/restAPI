package com.example.restapi.restapi;

import com.example.restapi.restapi.model.Product;
import com.example.restapi.restapi.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Product product1 = new Product();
        product1.setName("Test Pro");
        product1.setDescription("xxxxx");
        product1.setType("xxxxx");
        product1.setCategory("xxxx");

        productRepository.save(product1);

    }
}
