package microserviceExample.orderservice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import microserviceExample.orderservice.business.abstracts.OrderService;

import microserviceExample.orderservice.dto.OrderRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		this.orderService.placeHolder(orderRequest);
		return "Sipariş Alındı";
	}
}
