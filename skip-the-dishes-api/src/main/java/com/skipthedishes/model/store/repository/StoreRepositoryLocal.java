package com.skipthedishes.model.store.repository;

import java.util.Collection;

import javax.ejb.Local;

import com.skipthedishes.model.store.entity.Store;

@Local
public interface StoreRepositoryLocal {
	
	/**
	 * Get All Store in base.
	 * @return
	 */
	Collection<Store> findAll();

	/**
	 * Search all Store with contains {text} in name 
	 * @param text
	 * @return
	 */
	Collection<Store> search(String text);

	/**
	 * Find Store by ID.
	 * 
	 * @param id
	 * @return
	 */
	Store findById(Long id);
	
}


