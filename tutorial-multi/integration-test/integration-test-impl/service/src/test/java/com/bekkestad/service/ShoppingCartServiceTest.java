package com.bekkestad.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bekkestad.model.OrderDTO;
import com.bekkestad.model.OrderEntity;
import com.bekkestad.model.ProductDTO;
import com.bekkestad.model.ProductEntity;
import com.bekkestad.repository.OrderRepository;
import com.bekkestad.repository.OrderRepositoryImpl;
import com.bekkestad.service.ShoppingCartService;
import com.bekkestad.service.ShoppingCartServiceImpl;

@RunWith(Arquillian.class)
public class ShoppingCartServiceTest {

	private static final List<ProductDTO> PRODUCT_LIST = new ArrayList<ProductDTO>();
	private static final int NR_OF_PRODUCTS_TO_TEST = 4;
	private static Long orderId = 0L;
	static {
		
		for(int i = 0; i < NR_OF_PRODUCTS_TO_TEST; i++){
			ProductDTO product = new ProductDTO();
			
			product.setId(i+1L);
			product.setName("PRODUCT" + (i + 1));
			product.setDescription("This is product " + (i+1));
			product.setPrice(10D * (i + 1));
			PRODUCT_LIST.add(product);
		}		
	}
	
	@Deployment
	public static Archive<?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(ShoppingCartService.class, ShoppingCartServiceImpl.class, 
						ProductDTO.class, OrderDTO.class, OrderEntity.class, ProductEntity.class, 
						OrderRepository.class, OrderRepositoryImpl.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("jbossas-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");		
	}
	

	
	@Inject
	ShoppingCartServiceImpl shoppingCart;
		
	
	@Test
	@InSequence(1)
	public void should_be_able_to_add_products_to_cart(){	
		addProductsToCart();
		
		List<ProductDTO> productsInCart = shoppingCart.getShoppingCart();		
		Assert.assertEquals(NR_OF_PRODUCTS_TO_TEST, productsInCart.size());					
	}
	
	@Test
	@InSequence(2)
	public void should_be_able_to_get_the_shopping_cart(){
		addProductsToCart();
		
		List<ProductDTO> productsInCart = shoppingCart.getShoppingCart();
		
		int temp = 0;
		for(ProductDTO product : productsInCart){
			Assert.assertEquals(PRODUCT_LIST.get(temp).getId(), product.getId());
			Assert.assertEquals(PRODUCT_LIST.get(temp).getName(), product.getName());
			Assert.assertEquals(PRODUCT_LIST.get(temp).getDescription(), product.getDescription());
			Assert.assertEquals(PRODUCT_LIST.get(temp).getPrice(), product.getPrice());
			temp++;
		}		
		if(temp == 0)
			Assert.fail("THE CART IS EMPTY");
	}
	
	@Test
	@InSequence(3)
	public void should_be_able_to_remove_product_from_cart(){		
		addProductsToCart();
		
		int nrOfProductsToRemove = 2;
		for(int i = 0; i < nrOfProductsToRemove; i++){
			shoppingCart.removeProduct((i + 1)*1L);
		}
		List<ProductDTO> productsInCart = shoppingCart.getShoppingCart();		
		Assert.assertEquals(NR_OF_PRODUCTS_TO_TEST-nrOfProductsToRemove, productsInCart.size());
	}
	
	@Test
	@InSequence(4)
	public void should_be_able_to_place_order() throws Exception{
		addProductsToCart();
		orderId = shoppingCart.placeOrder();
		List<ProductDTO> productsInCart = shoppingCart.getShoppingCart();
		
		Assert.assertEquals(true, orderId>0);
		Assert.assertEquals(0, productsInCart.size());
	}
	
	@Test
	@InSequence(5)
	public void should_be_able_to_get_order_status() throws Exception{
		String status = shoppingCart.getOrderStatus(orderId);
		
		Assert.assertEquals("CREATED", status);
	}
	
	private void addProductsToCart(){		
		for(ProductDTO product : PRODUCT_LIST){
			shoppingCart.addProduct(product);
		}
	}
}
