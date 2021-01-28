package com.qa.ims.persistence.domain;

import java.util.List;
import java.util.stream.Stream;



public class Order {

	private Long id;
	private Customer customer;
	private double value;
	private List<Item> items;
	private Stream<Item> itemIteration = items.stream();
	
	public Order(Customer customer, List<Item> items) {
		this.setCustomer(customer);
		this.setItems(items);
	}
    
	public Order(Long id, Customer customer, List<Item> items) {
		this.setId(id);
		this.setCustomer(customer);
		this.setItems(items);
	}
	
	public Order(Customer customer, double value) {
		this.customer = customer;
		this.value = value;
	}
	
	public Order(Customer customer) {
		this.setCustomer(customer);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Stream<Item> getItemIteration() {
		return itemIteration;
	}

	public void setItemIteration(Stream<Item> itemIteration) {
		this.itemIteration = itemIteration;
	}

	@Override
	public String toString() {
		StringBuilder order = new StringBuilder();
		order.append(
				String.format("%s: %s %s", this.id, customer.getFirstName(), customer.getSurname()));
		if(this.items.isEmpty()) {
			order.append("\n No items found in this order.");
		} else {
			order.append(String.format(" value = £%.2f", this.value));
			this.items.forEach(item -> {
				order.append("\n -> ");
				order.append(String.format("%s: £%.2f", item.getName(), item.getPrice()));
			});
		}
		return order.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (getCustomer() == null) {
			if (other.getCustomer() != null)
				return false;
		} else if (!getCustomer().equals(other.getCustomer()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
	
	public double sumValue() {
		this.itemIteration.forEach(item -> 
		    value += item.getPrice());
		return value;
	}
	
	public Long getCustomerId(Customer customer) {
		return customer.getId();
	}
	
}
