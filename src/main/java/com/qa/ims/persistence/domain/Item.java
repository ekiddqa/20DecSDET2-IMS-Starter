package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String name;
	private double price;

	public Item(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	public Item(Long id, String name, double price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " price: " + (String.format("£%,.2f", price));
	}

	@Override
	public int hashCode() {
		String stringPrice =String.valueOf(price);  
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stringPrice == null) ? 0 : stringPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		String stringPrice = String.valueOf(price);
	 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		String otherStringPrice = String.valueOf(other.price); 
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stringPrice == null) {
			if (otherStringPrice != null)
				return false;
		} else if (!stringPrice.equals(otherStringPrice))
			return false;
		return true;
	}

}
