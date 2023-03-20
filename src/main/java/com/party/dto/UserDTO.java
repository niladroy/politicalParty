package com.party.dto;

import java.util.List;
import java.util.Objects;

import com.party.entity.Merchandise;

public class UserDTO {
	
	private int userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private List<ContactDTO> contacts;
	private List<EventDTO> events;
	private List<MerchandiseDTO> merchandises;
	private String role;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(int userId, String userName, String password) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<MerchandiseDTO> getMerchandises() {
		return merchandises;
	}
	public void setMerchandises(List<MerchandiseDTO> merchandises) {
		this.merchandises = merchandises;
	}
	public List<EventDTO> getEvents() {
		return events;
	}
	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}
	public List<ContactDTO> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactDTO> contacts) {
		this.contacts = contacts;
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
