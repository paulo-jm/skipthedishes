package com.skipthedishes.service.order;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.skipthedishes.model.order.entity.Order;
import com.skipthedishes.model.order.entity.OrderItem;
import com.skipthedishes.model.order.entity.OrderStatus;
import com.skipthedishes.model.order.repository.OrderRepositoryLocal;
import com.skipthedishes.service.order.OrderServiceLocal;

@Stateless
public class OrderService implements OrderServiceLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private OrderRepositoryLocal orderRepository;

	@Override
	public void createNewOrder(Order order) {
		
		order.setDate(LocalDate.now());
		order.setLastUpdate(LocalDate.now());
		order.setStatus(OrderStatus.ACTIVE);
		
		BigDecimal total = BigDecimal.ZERO;
		for (OrderItem item : order.getOrderItems()) {
			BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
			item.setTotal(itemTotal);
			
			total = total.add(itemTotal);
		}
		
		order.setTotal(total);
		
		orderRepository.save(order);
	}

}
