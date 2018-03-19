package com.skipthedishes.model.customer.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.skipthedishes.model.customer.entity.Customer;
import com.skipthedishes.model.customer.repository.CustomerRepositoryLocal;

@Stateless
public class CustomerRepository implements CustomerRepositoryLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Customer> customers;

	@Override
	public Customer findByEmail(String email) {
		
		return getCustomers()
			.stream()
			.filter(customer -> customer.getEmail().equalsIgnoreCase(email))
			.findFirst()
			.orElse(null);
		
	}

	@Override
	public void save(Customer customer) {

		Long id = Long.valueOf(getCustomers().size() + 1);
		customer.setId(id);
		customer.setCreation(LocalDate.now());

		customers.add(customer);

	}

	public List<Customer> getCustomers() {

		if (customers == null) {
			customers = new ArrayList<>();
		}

		return customers;
	}

}
