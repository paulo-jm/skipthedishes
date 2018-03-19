package com.skipthedishes.service.order;

import java.io.Serializable;

import javax.ejb.Local;
import javax.validation.Valid;

import com.skipthedishes.model.order.entity.Order;

@Local
public interface OrderServiceLocal extends Serializable {

	void createNewOrder(@Valid Order order);
	
}


