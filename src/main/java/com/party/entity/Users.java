package com.party.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	@Column(name="user_name")
	@JsonProperty("user_name")
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Contact> contacts;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> events;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="user_merch", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Merchandise> merchandises;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public List<Merchandise> getMerchandises() {
		return merchandises;
	}
	public void setMerchandises(List<Merchandise> merchandises) {
		this.merchandises = merchandises;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, userId, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	

}

