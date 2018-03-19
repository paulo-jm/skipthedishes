package com.skipthedishes.model.product.repository;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Local;

import com.skipthedishes.model.product.entity.Product;

@Local
public interface ProductRepositoryLocal extends Serializable {

	/**
	 * Get All product in base.
	 * @return
	 */
	Collection<Product> findAll();

	/**
	 * Search all product with contains {text} in name or description
	 * @param text
	 * @return
	 */
	Collection<Product> search(String text);

	/**
	 * Find Product by ID.
	 * 
	 * @param id
	 * @return
	 */
	Product findById(Long id);

	/**
	 * Find All Product by Store ID.
	 * 
	 * @param id
	 * @return
	 */
	Collection<Product> findAllProductsByStoreId(Long id);

}
