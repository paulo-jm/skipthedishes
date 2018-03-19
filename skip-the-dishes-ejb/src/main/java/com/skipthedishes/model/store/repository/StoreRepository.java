package com.skipthedishes.model.store.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.skipthedishes.model.store.entity.Store;
import com.skipthedishes.model.store.repository.StoreRepositoryLocal;

@Stateless
public class StoreRepository implements StoreRepositoryLocal {

	List<Store> stores;

	@PostConstruct
	private void setUp() {

		stores = new ArrayList<>();
		stores.add(new Store(Long.valueOf(1), "Dooney Food Store"));
		stores.add(new Store(Long.valueOf(2), "Mara's Fresh Food Store"));
		stores.add(new Store(Long.valueOf(3), "Doceria Food Shop"));

	}

	@Override
	public Collection<Store> findAll() {
		return stores;
	}

	@Override
	public Collection<Store> search(String text) {
		return stores
				.stream()
				.filter(
						product -> 
							product.getName().contains(text)
					)
				.collect(Collectors.toList());
	}

	@Override
	public Store findById(Long id) {
		return stores
				.stream()
				.filter(
						store -> store.getId().equals(id)
					)
				.findFirst()
				.orElse(null);
	}

}
