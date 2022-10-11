package microserviceExample.productservice.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import microserviceExample.productservice.entities.concretes.Product;

public interface ProductDao  extends JpaRepository<Product, String>{
	
	

}
