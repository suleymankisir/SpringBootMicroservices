package microserviceExample.orderservice.entities.concretes;

import java.math.BigDecimal;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long Id;
	
	private String skuCode;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
}
