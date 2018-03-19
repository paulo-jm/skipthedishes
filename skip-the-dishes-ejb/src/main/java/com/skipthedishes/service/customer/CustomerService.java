package com.skipthedishes.service.customer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;

import com.skipthedishes.model.customer.entity.Customer;
import com.skipthedishes.model.customer.repository.CustomerRepositoryLocal;
import com.skipthedishes.service.customer.CustomerServiceLocal;

@Stateless
public class CustomerService implements CustomerServiceLocal {
	
	@EJB
	private CustomerRepositoryLocal customerRepository; 

	@Override
	public void create(@Valid Customer customer) {
		customerRepository.save(customer);
	}

}


