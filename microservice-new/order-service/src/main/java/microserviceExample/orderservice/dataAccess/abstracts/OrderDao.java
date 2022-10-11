package microserviceExample.orderservice.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import microserviceExample.orderservice.entities.concretes.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

	
}
