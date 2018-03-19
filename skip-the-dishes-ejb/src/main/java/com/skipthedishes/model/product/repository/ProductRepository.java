package com.skipthedishes.model.product.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.skipthedishes.model.product.entity.Product;
import com.skipthedishes.model.product.repository.ProductRepositoryLocal;

@Stateless
public class ProductRepository implements ProductRepositoryLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;

	@PostConstruct
	private void setUp() {

		products = new ArrayList<>();

		products.add(new Product(Long.valueOf(1), Long.valueOf(1), "Pizza", "Pizza hot", BigDecimal.valueOf(12.0)));
		products.add(new Product(Long.valueOf(2), Long.valueOf(2), "Hot Dog", "Fine", BigDecimal.valueOf(15.0)));

	}

	@Override
	public Collection<Product> findAll() {
		return products;
	}

	@Override
	public Collection<Product> search(String text) {
		
		return products
				.stream()
				.filter(
						product -> 
							product.getDescription().contains(text) || 
							product.getName().contains(text)
					)
				.collect(Collectors.toList());
	}

	@Override
	public Product findById(Long id) {
		return products
				.stream()
				.filter(
						product -> product.getId().equals(id)
					)
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public Collection<Product> findAllProductsByStoreId(Long id) {
		return products
				.stream()
				.filter(
						product -> 
							product.getStoreId().equals(id)
							
					)
				.collect(Collectors.toList());
	}

}
