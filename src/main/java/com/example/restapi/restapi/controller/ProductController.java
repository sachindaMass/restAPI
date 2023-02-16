package com.example.restapi.restapi.controller;

import com.example.restapi.restapi.model.Product;
import com.example.restapi.restapi.repo.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products/")
@Api(value = "ProductsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the products with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public Optional<Product> getProduct(@PathVariable(name = "id") String id) {
        return productRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave) {
        return productRepository.save(productToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productToUpdate.getName());
                    product.setDescription(productToUpdate.getDescription());
                    product.setType(productToUpdate.getType());
                    product.setCategory(productToUpdate.getCategory());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    LOG.info("No products found with given id");
//                    return productRepository.save(productToUpdate);
                    return productToUpdate;
                });
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") String id) {

//        

    }
}
