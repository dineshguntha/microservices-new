package com.dguntha.inventoryservice;

import com.dguntha.inventoryservice.model.Inventory;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.dguntha.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication
{
    public static void main(final String[] args) {
        SpringApplication.run((Class)InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(final InventoryRepository inventoryRepository) {
        return args -> {
            inventoryRepository.deleteAll();
            final Inventory inventory = new Inventory();
            inventory.setSkuCode("iphone_13");
            inventory.setQuantity(Integer.valueOf(100));
            final Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("iphone_13_red");
            inventory2.setQuantity(Integer.valueOf(10));
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory2);
        };
    }
}