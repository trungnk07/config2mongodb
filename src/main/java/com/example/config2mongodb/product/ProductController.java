package com.example.config2mongodb.product;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository1;

    private ProductRepository productRepository2;

    public ProductController(@Qualifier("firstMongoTemplate") MongoTemplate template1,
                             @Qualifier("secondMongoTemplate") MongoTemplate template2) {
        MongoRepositoryFactory factory1 = new MongoRepositoryFactory(template1);
        this.productRepository1 = factory1.getRepository(ProductRepository.class);
        MongoRepositoryFactory factory2 = new MongoRepositoryFactory(template2);
        this.productRepository2 = factory2.getRepository(ProductRepository.class);
    }

    @PostMapping
    public void createProduct() {
        Product product1 = new Product();
        product1.setName("product 11");

        productRepository1.save(product1);

        Product product2 = new Product();
        product2.setName("product 22");

        productRepository2.save(product2);
    }

    @GetMapping
    public Product getProduct(@RequestParam("productName") String productName) {
        return productRepository2.getProductByName("product 22");
    }
}
