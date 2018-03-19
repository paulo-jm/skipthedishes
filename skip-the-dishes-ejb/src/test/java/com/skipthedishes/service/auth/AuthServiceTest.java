package com.skipthedishes.service.auth;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.skipthedishes.model.customer.entity.Customer;
import com.skipthedishes.model.customer.repository.CustomerRepository;
import com.skipthedishes.model.customer.repository.CustomerRepositoryLocal;
import com.skipthedishes.service.auth.AuthService;
import com.skipthedishes.service.auth.exception.AuthException;
import com.skipthedishes.service.customer.CustomerService;
import com.skipthedishes.service.customer.CustomerServiceLocal;

@RunWith(Arquillian.class)
public class AuthServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(AuthServiceLocal.class, AuthService.class, Customer.class, 
						CustomerRepositoryLocal.class, CustomerRepository.class,
						CustomerServiceLocal.class, CustomerService.class,
						AuthException.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(war.toString(true));
		return war;
	}
	
	@EJB
	private AuthServiceLocal authService;
	
	@EJB
	private CustomerServiceLocal customerService;
	
	private Customer customer;
	
	@Before
	public void bBefore() {
		
		customer = new Customer();
		customer.setEmail("paulojosemoreira@gmail.com");
		customer.setName("Paulo Jose");
		customer.setAddress("test address");
		customer.setPassword("123456");
		
		customerService.create(customer);
		
	}
	
	@Test(expected=AuthException.class)
	public void authUserWrongPasswordest() throws AuthException {
		authService.auth("paulojosemoreira@gmail.com", "123");
	}

	@Test(expected=AuthException.class)
	public void authUserNotRegistedTest() throws AuthException {
		authService.auth("paulojm@gmail.com", "123456");
	}
	
	@Test
	public void authTest() throws AuthException {
		authService.auth("paulojosemoreira@gmail.com", "123456");
	}

}
