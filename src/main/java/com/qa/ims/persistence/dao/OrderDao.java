package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDao implements IDomainDao<Order> {

	    public static final Logger LOGGER = LogManager.getLogger();
	    private ItemDao itemDao;
	    private CustomerDao customerDao;
	    
	    public OrderDao(ItemDao itemDao, CustomerDao customerDao) {
	    	super();
	    	this.itemDao = itemDao;
	    	this.customerDao = customerDao;
	    }

	    @Override
	    public Order create(Order order) {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statementOrder = connection
	                        .prepareStatement("INSERT INTO orders(fk_customer_id) VALUE (?)");) {
	        	statementOrder.setLong(1, order.getCustomer().getId());
	            statementOrder.executeUpdate(); 
	            return readLatest();
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }

	    public Order read(Long id) {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection.prepareStatement("SELECT orders.id as order_id, first_name.customers, "
	                		+ "surname.customers, name.item FROM orders, JOIN orders on order_item.fk_order_id=order.id, "
	                		+ "JOIN order_item on items.id = order_item.fk_item_id WHERE fk_order_id = ?");) {
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
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT orders.id as order_id, first_name.customers, surname.customers,"
	                		+ " name.item FROM orders, JOIN orders on order_item.fk_order_id=order.id,"
	                		+ " JOIN order_item on items.id = order_item.fk_item_id;");) {
	            List<Order> orders = new ArrayList<>();
	            while (resultSet.next()) {
	                orders.add(modelFromResultSet(resultSet));
	            }
	            return orders;
	        } catch (SQLException e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return new ArrayList<>();
	    }

	    public Order readLatest() {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT orders.id as order_id, first_name.customers, surname.customers,"
	                		+ " name.item FROM orders," + " JOIN orders on order_item.fk_order_id=order.id,"
	                		+ " JOIN order_item on items.id = order_item.fk_item_id ORDER BY id DESC LIMIT 1");) {
	            resultSet.next();
	            return modelFromResultSet(resultSet);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }
    
	    public List<Item> getItems(Order order) {
		    try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("SELECT order_item.fk_item_id, items.name, items.price FROM orders_items "
	                        		+ "JOIN order_items oi ON items.id = oi.fk_item_id WHERE oi.fk_item_id=?;")){
		    	List<Item> grabItems= new ArrayList<>();
	            statement.setLong(1, order.getId());
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	Long i = resultSet.getLong("fk_item_id");
	            	String name= resultSet.getString("name");
	            	Double price=resultSet.getDouble("price");
	            	grabItems.add(new Item(name,price));
	            }
	            return grabItems;
	        } catch (Exception e) {
	        	LOGGER.debug(e);
	        	LOGGER.error(e.getMessage());
	        }
	        return null;
		}
	    
	    public Order addItems(Long orderId, Long itemId) {
	    	   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
		                PreparedStatement statement = connection
		                        .prepareStatement("INSERT INTO orders_item (fk_order_id, fk_item_id) VALUE (?,)");) {
		        	statement.setLong(1, orderId);
		        	statement.setLong(2, itemId);
		            statement.executeUpdate(); 
		            return readLatest();
		        } catch (Exception e) {
		            LOGGER.debug(e);
		            LOGGER.error(e.getMessage());
		        }
		        return null;
		    }
	    
	    public double sumValue(Long id) {
	    	double sum = 0;
	    	itemList = 
	    	for(Item i: item) {
	    		
	    	}
	    	return sum;
	    }
	    
	    @Override
	    public Order update(Order order) {
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("UPDATE oWHERE id = ?, UPDATE order_item SET item");) {
	            order.getItem().stream().forEach(x -> statement.setLong(1, order.getCustomer().getId()),
	            statement.setLong(2, order.getItem().),
	            statement.setLong(3, order.getId()));
	            statement.executeUpdate();
	            return read(order.getId());
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }

	    @Override
	    public int delete(long id) { //delete an entire order
	        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();) {
	            return statement.executeUpdate("delete from orders where id = " + id + ", delete from order_items where fk_order_id = " + id);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        } return 0;
	    }
	        
	     public Order deleteItem(long id, long orderId) { //delete an item from an order
		      try (Connection connection = DatabaseUtilities.getInstance().getConnection();
		              Statement statement = connection.createStatement();) {
		         statement.executeUpdate("delete from orders_items where fk_item_id = " + id + " AND  fk_order_id = " + orderId);
		     } catch (Exception e) {
		          LOGGER.debug(e);
		          LOGGER.error(e.getMessage());
		     } return read(id);
	    }

	    @Override
	    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
	        Long id = resultSet.getLong("id");
	        Customer customer = customerDao.read(resultSet.getLong("fk_customer_id")); //init customerId
	        List<Item> item = getItem();//need to make a get items only from order method
	        return new Order(id, customer, item);
	    }

}
