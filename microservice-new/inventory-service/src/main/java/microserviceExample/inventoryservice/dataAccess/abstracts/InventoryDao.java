package microserviceExample.inventoryservice.dataAccess.abstracts;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import microserviceExample.inventoryservice.entities.concretes.Inventory;

public interface InventoryDao extends JpaRepository<Inventory, Long> {

	List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
