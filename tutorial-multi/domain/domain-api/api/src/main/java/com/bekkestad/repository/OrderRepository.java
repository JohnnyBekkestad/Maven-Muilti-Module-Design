package com.bekkestad.repository;

import java.util.List;

import javax.ejb.Local;

import com.bekkestad.model.OrderDTO;
import com.bekkestad.model.ProductDTO;

@Local
public interface OrderRepository {

	Long placeOrder(List<ProductDTO> order);	
	OrderDTO getOrder(Long orderId);	
}
