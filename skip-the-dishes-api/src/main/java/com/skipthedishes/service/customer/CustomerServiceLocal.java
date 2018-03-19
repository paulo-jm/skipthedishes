package com.skipthedishes.service.customer;

import javax.ejb.Local;
import javax.validation.Valid;

import com.skipthedishes.model.customer.entity.Customer;

@Local
public interface CustomerServiceLocal {
	void create(@Valid Customer customer);
}


