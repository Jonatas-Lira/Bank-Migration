package com.jala.ds3.market.Service.Details;

import com.google.gson.Gson;
import com.jala.ds3.market.Entity.Product;
import com.jala.ds3.market.Repository.ProductRepository;
import com.jala.ds3.market.Service.ProductMigrationService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela migração de dados de produtos do banco de dados PostgreSQL para o MongoDB.
 */
@Service
public class ProductMigrationServiceDetails implements ProductMigrationService {

    @Autowired
    private ProductRepository postgreRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Gson gson;

    /**
     * Migra todos os produtos do banco de dados PostgreSQL para o MongoDB.
     * Converte cada produto em formato JSON e salva no MongoDB.
     */
    @Override
    public void dataMigration() {

        List<Product> products = postgreRepository.findAll();

        for(Product product: products){

            String jsonString = gson.toJson(product);

            Document document = Document.parse(jsonString);

            mongoTemplate.save(document, "product");
        }
    }
}