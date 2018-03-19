package com.skipthedishes.model.store.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class Store implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String name;

	public Store() {
		super();
	}

	public Store(Long id, @NotBlank String name) {
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
