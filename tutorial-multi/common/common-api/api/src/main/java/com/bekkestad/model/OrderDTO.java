package com.bekkestad.model;

import java.util.List;

public class OrderDTO {
	private Long orderId;
	private String status;
	private List<ProductDTO> products;


	public OrderDTO() {
		super();
	}


	public Long getOrderId() {
		return orderId;
	}


	public List<ProductDTO> getProducts() {
		return products;
	}


	public String getStatus() {
		return status;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}


	public void setStatus(String status) {
		this.status = status;
	}

}
