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
	private Order testOrderStuff;
    private Item junk;
    private List<Item> pile;
    private List<Item> pileEmpty;
    private Customer customer;
	
	@Before
	public void setup() {
    DatabaseUtilities.connect();
    DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    final List<Item> pile = new ArrayList<>();
    final List<Item> pileEmpty = new ArrayList<>();
    Item junk = new Item(1L, "jordan", 50);
    pile.add(junk);
    final Customer customer = new Customer(1L, "jordan", "harrison");
    testOrder = new Order(2L, customer, pileEmpty, 0.0);
    testOrderStuff = new Order(1L, customer, pile, 0.0);

	}
	
    @Test
    public void testCreate() {
    	assertEquals(testOrder, DAO.create(testOrder));
    }

    @Test
    public void testReadAll() {
        final List<Order> expected = new ArrayList<>();
        expected.add(testOrderStuff);
        assertEquals(expected, DAO.readAll());
    }
    
    @Test
    public void testReadLatest() {
        assertEquals(testOrderStuff, DAO.readLatest());
    }

    @Test
    public void testRead() {
        assertEquals(testOrderStuff, DAO.read(1L));
    }

    @Test
    public void testUpdate() { //update for DAO always produces null as this became an add/remove items method in controller
    	final List<Item> junk = new ArrayList<>();
        final Order updated = new Order(1L, new Customer(1L, "jordan", "harrsion"), junk, 0.0);
        assertEquals(null, DAO.update(updated));


    }

    @Test
    public void testDelete() { // this deletes the entire order, rather than emptying the order or otherwise touching items in an order but leaving the order in tact.
        assertEquals(1, DAO.delete(1));
    }
    
    @Test
    public void testAddItems() {
    DAO.addItem(1L, 1L);

    }
    
    @Test
    public void testDeleteItems() {
    	DAO.deleteItem(1L, 1L);
    }
    
    @Test
    public void testGetItems() {
    	List<Item> stuff = new ArrayList<>();
    	stuff.add(new Item(1L, "jordan", 50));
    	System.out.println(stuff.toString());
    	assertEquals(stuff, DAO.getItems(testOrderStuff.getId()));
    }
}
