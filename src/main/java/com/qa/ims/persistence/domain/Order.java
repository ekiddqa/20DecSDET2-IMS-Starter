package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	Long id;
	Customer customer;
	Double value;
	List<Item> items;
	
	public Order(Customer Customer, List<Item> items) {
		this.setCustomer(Customer);
		this.setItem(items);
	}

	public Order(Long id, Customer customer, List<Item> items) {
		this.setId(id);
		this.setCustomer(customer);
		this.setItem(items);
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

	public List<Item> getItem() {
		return items;
	}

	public void setItem(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "id: " + id + " Customer Name: " + customer + " Items: " + items;
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
}
