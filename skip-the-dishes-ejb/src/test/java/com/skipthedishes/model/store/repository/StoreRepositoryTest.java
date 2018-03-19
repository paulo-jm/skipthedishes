package com.skipthedishes.model.store.repository;

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

import com.skipthedishes.model.store.entity.Store;
import com.skipthedishes.model.store.repository.StoreRepository;

@RunWith(Arquillian.class)
public class StoreRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Store.class, StoreRepositoryLocal.class, StoreRepository.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	private StoreRepositoryLocal storeRepository;

	@Test
	public void findAllTest() {
		assertTrue(storeRepository.findAll().size() == 3);
	}

	@Test
	public void searchTest() {
		assertTrue(storeRepository.search("Dooney").size() == 1);
		assertTrue(storeRepository.search("Fresh").size() == 1);
		assertTrue(storeRepository.search("Doceria").size() == 1);
	}

	@Test
	public void findByIdTest() {
		assertNotNull(storeRepository.findById(Long.valueOf(1)));
		assertNotNull(storeRepository.findById(Long.valueOf(2)));
		assertNull(storeRepository.findById(Long.valueOf(4)));
	}
	
}
