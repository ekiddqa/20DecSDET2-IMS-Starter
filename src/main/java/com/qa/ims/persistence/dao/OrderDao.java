package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDao implements IDomainDao<Order> {

	    public static final Logger LOGGER = LogManager.getLogger();
	    private CustomerDao customerDao;
	    private ItemDao itemDao;
	    
	    public OrderDao(ItemDao itemDao, CustomerDao customerDao) {
	    	super();
	    	this.itemDao = itemDao;
	    	this.customerDao = customerDao;
	    }

	    @Override
	    public Order create(Order order) {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statementOrder = connection
	                        .prepareStatement("INSERT INTO orders(fk_customer_id) VALUES (?)");) {
	        	statementOrder.setLong(1, order.getCustomer().getId());
	            statementOrder.executeUpdate(); 
	            return readLatest();
	        } catch (SQLException sqlE) {
	        	LOGGER.debug(sqlE);
	        	LOGGER.info("Customer with that ID does not exist.");
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }

	    public Order read(Long id) {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
	        	statement.setLong(1, id);
	        	ResultSet resultSet = statement.executeQuery();
	            resultSet.next();
	            return modelFromResultSet(resultSet);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }

	    @Override
	    public List<Order> readAll() {
	    	List<Order> orders = new ArrayList<>();
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
	            while (resultSet.next()) {
	                orders.add(modelFromResultSet(resultSet));
	            }
	            return orders;
	        } catch (SQLException e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return orders;
	    }
	    
	    public Order readLatest() {
	    	  try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                  Statement statement = connection.createStatement();
	                  ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
	              resultSet.next();
	              return modelFromResultSet(resultSet);
	          } catch (Exception e) {
	              LOGGER.debug(e);
	              LOGGER.error(e.getMessage());
	          }
	          return null;
	      }
	    
	    public List<Item> getItems(Long id) {
	    	List<Long> grabId = new ArrayList<>();
			List<Item> grabItem = new ArrayList<>();
	    	try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("SELECT * FROM order_item WHERE fk_order_id = ?");) {
	    		statement.setLong(1, id);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	Long itemId = resultSet.getLong("fk_item_id");
	            	grabId.add(itemId);
	            }
	            for(Long i : grabId) {
	            	grabItem.add(itemDao.read(i));
	            } 
	        } catch (Exception e) {
	        	LOGGER.debug(e);
	        	LOGGER.error(e.getMessage());
	        }
	    	return grabItem;
		}
	    
	    public Order addItem(Long orderId, Long itemId) {
	    	   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
		                PreparedStatement statement = connection
		                        .prepareStatement("INSERT INTO order_item (fk_order_id, fk_item_id) VALUE (?,?)");) {
		        	statement.setLong(1, orderId);
		        	statement.setLong(2, itemId);
		            statement.executeUpdate(); 
		            return readLatest();
		        } catch (SQLException sqlE) {
		        	LOGGER.debug(sqlE);
		        	LOGGER.info("Item with that ID does not exist.");
		        } catch (Exception e) {
		            LOGGER.debug(e);
		            LOGGER.error(e.getMessage());
		        }
		        return null;
		    }
	    
	    @Override
	    public Order update(Order order) { //Leftover from interface as this has been split into addItem and deleteItem
	        return null;
	    }

	    @Override
	    public int delete(long id) { //delete an entire order
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("DELETE FROM orders WHERE id = ?");) {
	        	statement.setLong(1, id);
	        	return statement.executeUpdate(); 
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        } return 0;
	    }
	        

	     public int deleteItem(Long orderId, Long itemId) { //delete an item from an order
	    	 try (Connection connection = DatabaseUtilities.getInstance().getConnection();
		                Statement statement = connection.createStatement();) {
		           return statement.executeUpdate("DELETE FROM order_item WHERE fk_item_id = " + itemId + " AND fk_order_id = " + orderId);
		     } catch (Exception e) {
		          LOGGER.debug(e);
		          LOGGER.error(e.getMessage());
		     } return 0;
	    }

	    @Override
	    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
	        Long id = resultSet.getLong("id");
	        Customer customer = customerDao.read(resultSet.getLong("fk_customer_id")); //gets the customerID from the database
	        List<Item> item = getItems(id); // then gets the Java object customer associated with that ID
	        double value = resultSet.getDouble("value");
	        return new Order(id, customer, item, value);
	    }

}
