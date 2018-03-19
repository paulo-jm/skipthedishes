package com.skipthedishes.service.auth;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.skipthedishes.model.customer.entity.Customer;
import com.skipthedishes.model.customer.repository.CustomerRepositoryLocal;
import com.skipthedishes.service.auth.AuthServiceLocal;
import com.skipthedishes.service.auth.exception.AuthException;

@Stateless
public class AuthService implements AuthServiceLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CustomerRepositoryLocal customerRepository;

	@Override
	public void auth(String email, String password) throws AuthException {
		
		Customer customer = customerRepository.findByEmail(email);
		
		if(customer == null) {
			throw new AuthException("Email not Found");
		} else if (!customer.getPassword().equals(password)) {
			throw new AuthException("Invalid Password");
		} 
		
	}

}
