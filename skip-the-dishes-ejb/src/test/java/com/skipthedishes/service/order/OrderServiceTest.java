package com.skipthedishes.service.order;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

import com.skipthedishes.model.order.entity.Order;
import com.skipthedishes.model.order.entity.OrderItem;
import com.skipthedishes.model.order.entity.OrderStatus;
import com.skipthedishes.model.order.repository.OrderRepository;
import com.skipthedishes.model.order.repository.OrderRepositoryLocal;
import com.skipthedishes.service.order.OrderService;

@RunWith(Arquillian.class)
public class OrderServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(Order.class, OrderItem.class, OrderStatus.class, OrderServiceLocal.class, OrderService.class,
						OrderRepositoryLocal.class, OrderRepository.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(war.toString(true));
		return war;
	}

	@EJB
	private OrderServiceLocal orderService;
	
	@EJB
	private OrderRepositoryLocal OrderRepository;

	private Order order;

	@Before
	public void before() {

		order = new Order();
		order.setContact("Contact");
		order.setCustomerId(Long.valueOf(1));
		order.setDeliveryAddress("deliveryAddress");
		order.setStatus(OrderStatus.ACTIVE);
		order.setStoreId(Long.valueOf(1));

		OrderItem orderItem = new OrderItem();
		orderItem.setPrice(BigDecimal.valueOf(4));
		orderItem.setProductId(Long.valueOf(1));
		orderItem.setQuantity(Long.valueOf(4));
		
		Set<OrderItem> orderItems = new HashSet<>();
		orderItems.add(orderItem);
		
		order.setOrderItems(orderItems);
		
	}
	
	@Test
	public void createNewOrderTest() {
		assertTrue(OrderRepository.findAll().size() == 0);
		orderService.createNewOrder(order);
		assertFalse(OrderRepository.findAll().size() == 0);
		
		assertNotNull(order.getId());
		assertTrue(order.getTotal().equals(BigDecimal.valueOf((16))));
	}
	
}
