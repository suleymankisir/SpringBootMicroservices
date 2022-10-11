package microserviceExample.inventoryservice.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import microserviceExample.inventoryservice.business.abstracts.InventoryService;
import microserviceExample.inventoryservice.dataAccess.abstracts.InventoryDao;
import microserviceExample.inventoryservice.dtos.InventoryResponse;


@Service
@RequiredArgsConstructor
public class InventoryManager implements InventoryService {

	@Autowired
	private InventoryDao inventoryDao;

	@Override
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		
		return this.inventoryDao.findBySkuCodeIn(skuCode).stream()
				.map(inventory -> 
					InventoryResponse.builder()
						.skuCode(inventory.getSkuCode())
						.isInStock(inventory.getQuantity() > 0)
						.build()
		        ).toList();
	}
	
	
	
}
