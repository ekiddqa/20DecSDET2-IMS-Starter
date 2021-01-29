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

    final CustomerDao CDAO = new CustomerDao();
    final ItemDao IDAO = new ItemDao();
    final OrderDao DAO = new OrderDao(IDAO, CDAO);
    final Customer cust = new Customer(1L,"jordan", "harrison");
    final List<Item> stuff = new ArrayList<>();
    final Order created = new Order(1L,cust,stuff);

    @SuppressWarnings("unused")
	@Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        final Customer nCust = new Customer(1L,"nick", "barba");
        expected.add(new Order(nCust));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Order(cust), DAO.readLatest());
    }

    @Test
    public void testRead() {
    	 final long ID = 1L;
         assertEquals(new Order(ID, cust), DAO.read(ID));
    }

    @Test
    public void testUpdate() {
    	final Customer nCust = new Customer(1L, "nick", "johnson");
        final Order updated = new Order(1L, nCust);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
}
