package com.ravi.inventoryservice;

import com.ravi.inventoryservice.model.Inventory;
import com.ravi.inventoryservice.repository.Inventoryrepsoitory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);


	}
	@Bean
	public CommandLineRunner loadData(Inventoryrepsoitory inventoryRepositoy){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone13");
			inventory.setQuantity(100);


			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone13_black");
			inventory1.setQuantity(0);

			inventoryRepositoy.save(inventory);
			inventoryRepositoy.save(inventory1);
		};
	}

}
