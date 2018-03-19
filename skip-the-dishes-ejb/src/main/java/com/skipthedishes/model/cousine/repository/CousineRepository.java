package com.skipthedishes.model.cousine.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.skipthedishes.model.cousine.entity.Cousine;

@Stateless
public class CousineRepository implements CousineRepositoryLocal {

	
	private List<Cousine>  cousines;

	@PostConstruct
	private void setUp() {

		cousines = new ArrayList<>();

		cousines.add(new Cousine(Long.valueOf(1), "Indian Food"));
		cousines.add(new Cousine(Long.valueOf(2), "Brazilian Food"));
		cousines.add(new Cousine(Long.valueOf(2), "Pizza"));
		
	}
	
	@Override
	public Collection<Cousine> findAll() {
		return cousines;
	}

	@Override
	public Collection<Cousine> search(String text) {
		return cousines
				.stream()
				.filter(
						cousine -> 
							cousine.getName().contains(text)
					)
				.collect(Collectors.toList());
	}

	@Override
	public Cousine findById(Long id) {
		return cousines
				.stream()
				.filter(
						cousine -> cousine.getId().equals(id)
					)
				.findFirst()
				.orElse(null);
	}

}
