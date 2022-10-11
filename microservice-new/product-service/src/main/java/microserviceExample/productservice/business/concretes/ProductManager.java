package microserviceExample.productservice.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microserviceExample.productservice.business.abstracts.ProductService;
import microserviceExample.productservice.dataAccess.abstracts.ProductDao;
import microserviceExample.productservice.dto.ProductRequest;
import microserviceExample.productservice.dto.ProductResponse;
import microserviceExample.productservice.entities.concretes.Product;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductManager implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public void createProduct(ProductRequest productRequest) {
		
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();		
		this.productDao.save(product);
		log.info("product {} is saved",product.getId());
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		
		List<Product> products = this.productDao.findAll();
		
		return products.stream().map(product -> mapToProductResponse(product)).toList();
		
		
	}

	private ProductResponse mapToProductResponse(Product product) {
		
		return ProductResponse.builder()
				.Id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

	


}
