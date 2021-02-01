package com.qa.ims.persistence.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;
import com.qa.ims.utils.JavaUtilities;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerControllerTest {
	private final CustomerDao DAO = new CustomerDao();
	private final Customer testCustomer = new Customer("jordan", "harrison");
	private JavaUtilities javaUtilities;
	private final CustomerController controller = new CustomerController(DAO, javaUtilities);
	
    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
        final CustomerDao DAO = new CustomerDao();
    	final Customer testCustomer = new Customer("jordan", "harrison");
    	final CustomerController controller = new CustomerController(DAO, javaUtilities);
    }
    
    @Test
    public void testReadAll() {
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(1L, "jordan", "harrison"));
        assertEquals(expected, controller.readAll());
    }
    
//other functionality testing requires mockito
   

}
