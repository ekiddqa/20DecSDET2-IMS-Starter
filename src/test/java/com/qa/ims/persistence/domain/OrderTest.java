package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {
	List<Item> testItems = new ArrayList<>();
	Order testOrder = new Order(null, null, testItems, 0.0);
	Item testItem = new Item("jordan", 50);
	Customer testCustomer = new Customer("jordan", "harrison");

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void testCalulateValueZero() {
		assertEquals(testOrder.getValue(), testOrder.calculateValue());
	}
	
	@Test
	public void testCalulateValue() {
		testItems.add(testItem);
		assertEquals(testItem.getPrice(), testOrder.calculateValue());
	}
	
	@Test
	public void testGetToString() {
		Customer testCustomer = new Customer("jordan", "harrison");
		Order testOrderToString = new Order(2L, testCustomer, testItems, 0.0);
		System.out.println(testOrderToString);
	}
	
	@Test
	public void testGetToStringWItems() {

		testItems.add(testItem);
		Order testOrderToStringDuo = new Order(2L, testCustomer, testItems, 0.0);
		System.out.println(testOrderToStringDuo);
	}
	
	@Test
	public void testGetCustomer() {
		Order testOrderCustomer = new Order(3L, testCustomer, testItems, 0.0);
        System.out.print(testOrderCustomer.getCustomer());
	}
	
	@Test
	public void testGetItems() {
		testOrder.getItems();
		System.out.println(testOrder.getItems());
		assertEquals(testItems, testOrder.getItems());
	}
	
	@Test
	public void testGetId() {
		assertEquals(null, testOrder.getId());
	}
	
}

