package com.party.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Merchandise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	private String itemName;
	private int price;
	@ManyToMany(mappedBy = "merchandises")
	private List<Users> users;
	
	
	public Merchandise() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Merchandise(int itemId, String itemName, int price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
	}


	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public List<Users> getUsers() {
		return users;
	}


	public void setUsers(List<Users> users) {
		this.users = users;
	}


	@Override
	public int hashCode() {
		return Objects.hash(itemId, itemName, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Merchandise other = (Merchandise) obj;
		return itemId == other.itemId && Objects.equals(itemName, other.itemName) && price == other.price;
	}
	@Override
	public String toString() {
		return "Merchandise [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + "]";
	}
	
	
}
