package microserviceExample.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import microserviceExample.inventoryservice.dataAccess.abstracts.InventoryDao;
import microserviceExample.inventoryservice.entities.concretes.Inventory;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
		

	}
	
	@Bean
	public CommandLineRunner loadData(InventoryDao inventoryDao) {
	
		return args -> {
			
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);
			
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);
			
			inventoryDao.save(inventory);
			inventoryDao.save(inventory1);
			
			
		};
		
	}

}
