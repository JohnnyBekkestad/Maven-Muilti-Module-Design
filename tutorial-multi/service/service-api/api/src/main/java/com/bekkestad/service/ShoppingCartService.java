package com.bekkestad.service;

import java.util.List;

import com.bekkestad.model.ProductDTO;

public interface ShoppingCartService {

	void addProduct(ProductDTO product);

	void removeProduct(Long productId);

	List<ProductDTO> getShoppingCart();

	Long placeOrder() throws Exception;

	String getOrderStatus(Long orderId) throws Exception;

}