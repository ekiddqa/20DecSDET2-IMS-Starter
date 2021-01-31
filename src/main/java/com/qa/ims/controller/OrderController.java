package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;

@SuppressWarnings("unused")
public class OrderController implements ICrudController<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDao orderDao;
	private CustomerDao customerDao = new CustomerDao();
    private JavaUtilities javaUtilities;
    

    public OrderController(OrderDao orderDao, JavaUtilities javaUtilities) {
        super();
        this.orderDao = orderDao;
        this.javaUtilities = javaUtilities;
    }

	/*
	 * @Override public List<Order> readAll() { List<Order> orders =
	 * orderDao.readAll(); if(orders.isEmpty()) { LOGGER.info("No orders in IMS.");
	 * } else { for (Order order : orders) { LOGGER.info(order); } } return orders;
	 * }
	 */
    
    @Override 
    public Order create() {
    	LOGGER.info("Please enter a customer ID."); //
    		Long customerId = javaUtilities.getLong();
    		Order order = orderDao.create(new Order(new Customer(customerId, null, null), 0.0));
   	        return order; 
    }
   
    @Override
    public List<Order> readAll() {
    	LOGGER.info("Would you like to: 1) View all orders, 2) View specific order \n Enter the number of your choice. All other input exits menu.");
    	Long input = javaUtilities.getLong();
    	if(input == 1) {
    		List<Order> orders = orderDao.readAll();
    		for (Order order: orders) {
    			LOGGER.info(order);	
    		} return orders;
			} else if(input == 2) {
			 LOGGER.info("Enter ID of the order whose total you wish to view."); 
			 Long id = javaUtilities.getLong();
		     LOGGER.info(orderDao.read(id));
			 return null;
			} else {
    		LOGGER.info("Leaving read orders.");
    	}
		return null;
    	
    }


	@Override
	public Order update() {
		Order nullOrder = null;  
		LOGGER.info("Please enter the id of the order you would like to update");
	        Long id = javaUtilities.getLong();
	        LOGGER.info("Would you like to add or remove an item? Other to exit menu.");
	        String input = javaUtilities.getString().toLowerCase();
	        if(input.equals("add")) {
	        	LOGGER.info("Enter ID of item to add to order.");
	        	Long toAdd = javaUtilities.getLong();
	        	nullOrder = this.orderDao.addItems(id, toAdd);
	        } else if(input.equals("remove")) {
	            LOGGER.info("Enter ID of item to remove from order.");
	            Long toRemove = javaUtilities.getLong();
	            orderDao.deleteItem(id, toRemove);
	        }	else {
	        	LOGGER.info("Leaving update. Only \"add\" and \"remove\" allowed.");
	        }
	        	return nullOrder;
	        	
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = javaUtilities.getLong();
        return orderDao.delete(id);
	}
	
}
