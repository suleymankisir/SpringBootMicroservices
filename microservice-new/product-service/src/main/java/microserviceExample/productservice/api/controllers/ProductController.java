package microserviceExample.productservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import microserviceExample.productservice.business.abstracts.ProductService;
import microserviceExample.productservice.dto.ProductRequest;
import microserviceExample.productservice.dto.ProductResponse;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		
		this.productService.createProduct(productRequest);
		
	}
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		
		return this.productService.getAllProducts();
		
	}
	
}
