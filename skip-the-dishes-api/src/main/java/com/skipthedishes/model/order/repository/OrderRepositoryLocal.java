package com.skipthedishes.model.order.repository;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Local;

import com.skipthedishes.model.order.entity.Order;

@Local
public interface OrderRepositoryLocal extends Serializable {

	void save(Order order);

	Collection<Order> findAll();

	Order findById(Long id);

	Collection<Order> findOrdersByCustomerId(Long id);

}
