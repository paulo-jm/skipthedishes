package com.skipthedishes.model.order.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.skipthedishes.service.order.OrderService;
import com.skipthedishes.service.order.OrderServiceLocal;

@RunWith(Arquillian.class)
public class OrderRepositoryTest {

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
	private OrderRepositoryLocal orderRepository;

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
		
		orderService.createNewOrder(order);
		
	}
	
	@Test
	public void findByIdTest() {
		assertNotNull(orderRepository.findById(Long.valueOf(1)));
		assertNull(orderRepository.findById(Long.valueOf(7)));
	}
	
	@Test
	public void findOrdersByCustomerIdTest() {
		
		order = new Order();
		order.setContact("Contact");
		order.setCustomerId(Long.valueOf(2));
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
		
		orderService.createNewOrder(order);
		
		assertNotNull(orderRepository.findAll().size() == 2);
		
		assertNotNull(orderRepository.findOrdersByCustomerId(Long.valueOf(1)));
		assertNotNull(orderRepository.findOrdersByCustomerId(Long.valueOf(2)));
	}
	
}
