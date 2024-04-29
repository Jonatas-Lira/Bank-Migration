package com.jala.ds3.market.Controller;

import com.jala.ds3.market.Service.ProductMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api-v1")
public class ProductController {

    @Autowired
    private ProductMigrationService service;

    @PostMapping("/migration-date")
    public String migrationDate(){

        try {
            service.dataMigration();
            return "Migration sucess";
        }catch (Exception exception){
            return "Migration failed";
        }
    }
}
