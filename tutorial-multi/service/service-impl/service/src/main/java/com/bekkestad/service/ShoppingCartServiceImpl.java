package com.bekkestad.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import com.bekkestad.model.ProductDTO;
import com.bekkestad.repository.OrderRepository;

@SessionScoped
public class ShoppingCartServiceImpl implements Serializable, ShoppingCartService {

	private static final long serialVersionUID = 1L;
	private Map<Long, ProductDTO> shoppingCartMap;
	
	@EJB
	private OrderRepository order;

	@Override
	public void addProduct(ProductDTO product){
		shoppingCartMap.put(product.getId(), product);		
	}
	
	@Override
	public void removeProduct(Long productId){
		shoppingCartMap.remove(productId);
	}
	
	@Override
	public List<ProductDTO> getShoppingCart(){		
		return Collections.unmodifiableList(new ArrayList<ProductDTO>(shoppingCartMap.values()));			
	}
	
	@Override
	public Long placeOrder() throws Exception{
		Long orderId = order.placeOrder(getShoppingCart());
		shoppingCartMap.clear();
		return orderId;			
	}
	
	@Override
	public String getOrderStatus(Long orderId) throws Exception{
		return order.getOrder(orderId).getStatus();
	}
	
	@PostConstruct
	void initialize(){
		shoppingCartMap = new HashMap<Long, ProductDTO>();
	}
}
