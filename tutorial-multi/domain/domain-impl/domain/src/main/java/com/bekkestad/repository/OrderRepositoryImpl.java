package com.bekkestad.repository;

import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.bekkestad.model.OrderDTO;
import com.bekkestad.model.OrderEntity;
import com.bekkestad.model.ProductDTO;
import com.bekkestad.model.ProductEntity;

@Singleton
@Lock(LockType.READ)
public class OrderRepositoryImpl implements OrderRepository {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Lock(LockType.WRITE)
	//@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long placeOrder(List<ProductDTO> products) {
		
		OrderEntity orderEntity = new OrderEntity();
		try {
			for(ProductDTO productDAO : products){
				ProductEntity productEntity = ProductEntity.fromDAO(productDAO);
				em.persist(productEntity);
				orderEntity.addProduct(productEntity);					
			}
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
				
		
		try {
			em.persist(orderEntity);
			em.flush();	
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return orderEntity.getId();
	}

	@Override
	public OrderDTO getOrder(Long orderId) {		
		OrderEntity orderEntity = null;
		
		try {						
			orderEntity = (OrderEntity)em.createNamedQuery(OrderEntity.FIND_ORDER)
				.setParameter("orderId", orderId).getSingleResult();
					
		} catch (NoResultException nre) {
			nre.printStackTrace();						
		}
		
		return orderEntity.asDAO();
	}

}
