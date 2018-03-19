package com.skipthedishes.model.cousine.entity;

public class Cousine {

	private Long id;
	
	private String name;
	
	public Cousine() {
		super();
	}

	public Cousine(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

