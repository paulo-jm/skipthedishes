package com.skipthedishes.model.cousine.repository;

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

import com.skipthedishes.model.cousine.entity.Cousine;

@RunWith(Arquillian.class)
public class CousineRepositoryTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Cousine.class, CousineRepositoryLocal.class, CousineRepository.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	private CousineRepositoryLocal cousineRepository;

	@Test
	public void findAllTest() {
		assertTrue(cousineRepository.findAll().size() == 3);
	}

	@Test
	public void searchTest() {
		assertTrue(cousineRepository.search("Indian Food").size() == 1);
		assertTrue(cousineRepository.search("Brazilian Food").size() == 1);
		assertTrue(cousineRepository.search("Pizza").size() == 1);
	}

	@Test
	public void findByIdTest() {
		assertNotNull(cousineRepository.findById(Long.valueOf(1)));
		assertNotNull(cousineRepository.findById(Long.valueOf(2)));
		assertNull(cousineRepository.findById(Long.valueOf(4)));
	}
	
	
}
