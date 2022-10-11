package microserviceExample.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductResponse {
	
	private String Id;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;
	

}
