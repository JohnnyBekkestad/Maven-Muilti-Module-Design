package com.bekkestad.model;

import com.bekkestad.model.OrderDTO;

public class ProductDTO {
	private Long id;
	private String description;
	private String name;
	private Double price; 
	private OrderDTO order;
	/**
	 * 
	 */
	public ProductDTO() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public Double getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
