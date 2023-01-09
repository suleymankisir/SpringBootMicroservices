package microserviceExample.orderservice.business.concretes;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import microserviceExample.orderservice.business.abstracts.OrderService;
import microserviceExample.orderservice.dataAccess.abstracts.OrderDao;
import microserviceExample.orderservice.dto.InventoryResponse;
import microserviceExample.orderservice.dto.OrderLineItemsDto;
import microserviceExample.orderservice.dto.OrderRequest;
import microserviceExample.orderservice.entities.concretes.Order;
import microserviceExample.orderservice.entities.concretes.OrderLineItems;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderManager implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	public void placeHolder(OrderRequest orderRequest) {
		
		Order order = new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes =  order.getOrderLineItemsList().stream()
		.map(OrderLineItems::getSkuCode)
		.toList();
		
		//call invertory service
		InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();    //WebClient is an asynchronous structure, but the "block" method can be used to synchronize it.
		
		boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
				.allMatch(inventoryResponse -> inventoryResponse.isInStock());
		
		if(allProductsInStock)
			this.orderDao.save(order);
		else
			throw new IllegalArgumentException("product bulunmuyor");
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		
		return orderLineItems;
	}
	
}
