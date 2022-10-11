package microserviceExample.inventoryservice.business.abstracts;

import java.util.List;

import microserviceExample.inventoryservice.dtos.InventoryResponse;

public interface InventoryService {

	List<InventoryResponse> isInStock(List<String> skuCode);
	
	
}
