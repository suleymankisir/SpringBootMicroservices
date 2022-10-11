package microserviceExample.productservice.business.abstracts;

import java.util.List;

import microserviceExample.productservice.dto.ProductRequest;
import microserviceExample.productservice.dto.ProductResponse;

public interface ProductService {
	
	void createProduct(ProductRequest productRequest);
	
	List<ProductResponse> getAllProducts();

}
