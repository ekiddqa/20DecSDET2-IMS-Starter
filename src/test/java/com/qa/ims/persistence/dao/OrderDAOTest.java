package com.qa.ims.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDAOTest {
	
	private final CustomerDao cDAO = new CustomerDao();
	private final ItemDao iDAO = new ItemDao();
	private final OrderDao DAO = new OrderDao(iDAO, cDAO);
	private Order testOrder;
    private Item junk;
    private List<Item> pile;
    private Customer customer;
	
	@Before
	public void setup() {
    DatabaseUtilities.connect();
    DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    final List<Item> pile = new ArrayList<>();
    Item junk = new Item(1L, "jordan", 50);
    final Customer customer = new Customer(1L, "jordan", "harrison");
    testOrder = new Order(2L, customer, pile, 0.0);

	}
	
    @Test
    public void testCreate() {
    	final Order created = testOrder;
    	assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {

        final List<Order> expected = new ArrayList<>();
        pile.add(junk);
        final Order created = new Order(1L, new Customer(1L, "jordan", "harrison"), pile);
        expected.add(created);
        assertEquals(expected, DAO.readAll());
    }
    
    @Test
    public void testReadLatest() {
    	Order testOrder1 = new Order(1L, customer, pile, 0.0);
        assertEquals(testOrder1, DAO.readLatest());
    }

    @Test
    public void testRead() {
    	List<Item> junk = new ArrayList<>();
    	final Order order = new Order(1L, new Customer("jordan", "harrison"), junk, 0.0);
        assertEquals(order, DAO.read(1L));
    }

    @Test
    public void testUpdate() {
    	
    	final List<Item> junk = new ArrayList<>();
        final Order updated = new Order(1L, new Customer(1L, "jordan", "harrsion"), junk, 0.0);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
}
