package com.jala.ds3.market.migration;

import com.jala.ds3.market.Entity.Product;
import com.jala.ds3.market.Repository.ProductRepository;
import com.jala.ds3.market.Service.ProductMigrationService;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
public class ProductMigrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMigrationService productMigrationService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {

        productRepository.deleteAll();
        mongoTemplate.dropCollection("product");

        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0f);
        productRepository.save(product);
    }

    @Test
    public void testMigration() {
        productMigrationService.dataMigration();

        List<Document> productsInMongo = mongoTemplate.findAll(Document.class, "product");
        assertEquals(1, productsInMongo.size(), "Devia haver 1 produto no MongoDB após a migração");

        Document migratedProduct = productsInMongo.get(0);
        assertEquals("Test Product", migratedProduct.getString("name"), "Nome do produto não corresponde");
        assertEquals(10.0, migratedProduct.getDouble("price"), "Preço do produto não corresponde");
    }
}
