package com.skipthedishes.model.cousine.repository;

import java.util.Collection;

import javax.ejb.Local;

import com.skipthedishes.model.cousine.entity.Cousine;

@Local
public interface CousineRepositoryLocal {

	Collection<Cousine> findAll();
	
	Collection<Cousine>  search(String text);
	
	Cousine findById(Long id);
	
}




