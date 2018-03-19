package com.skipthedishes.model.order.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;

import com.skipthedishes.model.order.entity.Order;
import com.skipthedishes.model.order.entity.OrderItem;
import com.skipthedishes.model.order.repository.OrderRepositoryLocal;

@Stateless
public class OrderRepository implements OrderRepositoryLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Order> orders;

	@Override
	public Collection<Order> findAll() {
		return getOrders();
	}

	@Override
	public void save(Order order) {

		Long id = Long.valueOf(getOrders().size() + 1);
		order.setId(id);
		Long idOrderItem = Long.valueOf(1);
		for (OrderItem item : order.getOrderItems()) {
			item.setId(idOrderItem);
			item.setOrderId(id);
			idOrderItem++;
		}
		
		orders.add(order);

	}
	
	@Override
	public Order findById(Long id) {
		return getOrders()
				.stream()
				.filter(order -> order.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	public List<Order> getOrders() {

		if (orders == null) {
			orders = new ArrayList<>();
		}

		return orders;
	}

	@Override
	public Collection<Order> findOrdersByCustomerId(Long id) {
		return getOrders()
				.stream()
				.filter(
						order -> order.getCustomerId().equals(id) 
					)
				.collect(Collectors.toList());
	}

}
