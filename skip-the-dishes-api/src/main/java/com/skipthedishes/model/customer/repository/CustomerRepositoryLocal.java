package com.skipthedishes.model.customer.repository;

import java.io.Serializable;

import javax.ejb.Local;

import com.skipthedishes.model.customer.entity.Customer;

@Local
public interface CustomerRepositoryLocal extends Serializable {

	 Customer findByEmail(String email);
	
	 void save(Customer customer);
	
}

