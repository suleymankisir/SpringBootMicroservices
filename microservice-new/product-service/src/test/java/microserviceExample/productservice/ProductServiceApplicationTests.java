package microserviceExample.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import microserviceExample.productservice.dataAccess.abstracts.ProductDao;
import microserviceExample.productservice.dto.ProductRequest;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc

class ProductServiceApplicationTests {
	
    
	@Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
      .withDatabaseName("Microservice")
      .withUsername("postgres")
      .withPassword("12345");
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ProductDao productDao;
    
    
    
    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
    	registry.add("spring.datasource.url", postgres::getJdbcUrl);
    	registry.add("spring.datasource.username", postgres::getUsername);
    	registry.add("spring.datasource.password", postgres::getPassword);
    }

	@Test
	void shouldCreateProduct() throws Exception {
		
		ProductRequest productRequest = getProductRequest();
		String productRequestString =  objectMapper.writeValueAsString(productRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
			Assertions.assertEquals(1,this.productDao.findAll().size());
		
		
	}

	private ProductRequest getProductRequest() {
		
		return ProductRequest.builder()
				.name("iphone 13")
				.description("iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
		
	}

	
	
}



