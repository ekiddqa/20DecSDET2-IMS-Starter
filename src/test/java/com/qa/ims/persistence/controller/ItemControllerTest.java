package com.qa.ims.persistence.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DatabaseUtilities;
import com.qa.ims.utils.JavaUtilities;

public class ItemControllerTest {
	private final ItemDao DAO = new ItemDao();
	private final Item testItem = new Item("jordan", 50);
	private JavaUtilities javaUtilities;
	private final ItemController controller = new ItemController(DAO, javaUtilities);
	
    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
        final ItemDao DAO = new ItemDao();
    	final Item testItem = new Item("jordan", 50);
    	final ItemController controller = new ItemController(DAO, javaUtilities);
    }
    
    @Test
    public void testReadAll() {
        List<Item> expected = new ArrayList<>();
        expected.add(new Item(1L, "jordan", 50));
        assertEquals(expected, controller.readAll());
    }
    
//other functionality testing requires mockito
   

}