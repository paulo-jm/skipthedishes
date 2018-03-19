package com.skipthedishes.service.customer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.skipthedishes.service.customer.CustomerService;

@RunWith(Arquillian.class)
public class CustomerServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Customer.class, CustomerRepositoryLocal.class, CustomerRepository.class,
						CustomerServiceLocal.class, CustomerService.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	private CustomerRepositoryLocal customerRepository;

	@EJB
	private CustomerServiceLocal customerService;

	private Customer customer;

	@Before
	public void before() {

		customer = new Customer();
		customer.setEmail("paulojosemoreira@gmail.com");
		customer.setName("Paulo Jose");
		customer.setAddress("TEste Address");
		customer.setPassword("123456");

	}

	@Test
	public void createTest() {

		assertNull(customerRepository.findByEmail("paulojosemoreira@gmail.com"));
		customerService.create(customer);
		assertNotNull(customerRepository.findByEmail("paulojosemoreira@gmail.com"));

	}

}
