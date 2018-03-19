package com.skipthedishes.model.product.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.skipthedishes.model.product.entity.Product;
import com.skipthedishes.model.product.repository.ProductRepository;

@RunWith(Arquillian.class)
public class ProductRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Product.class, ProductRepositoryLocal.class, ProductRepository.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	private ProductRepositoryLocal productRepository;

	@Test
	public void findAllTest() {
		assertTrue(productRepository.findAll().size() == 2);
	}

	@Test
	public void searchTest() {
		assertTrue(productRepository.search("Fine").size() == 1);
		assertTrue(productRepository.search("Hot Dog").size() == 1);
		assertTrue(productRepository.search("Pizza").size() == 1);
	}

	@Test
	public void findByIdTest() {
		assertNotNull(productRepository.findById(Long.valueOf(1)));
		assertNotNull(productRepository.findById(Long.valueOf(2)));
		assertNull(productRepository.findById(Long.valueOf(4)));
	}
	
	@Test
	public void findAllProductsByStoreIdTest() {
		assertTrue(productRepository.findAllProductsByStoreId(Long.valueOf(1)).size() == 1);
	}

}