package com.party.dto;

import java.util.*;
public class ContactDTO {

    private String name;
	private String description;
	private int contactId;


    public ContactDTO() {}
	public ContactDTO(int contactId, String name , String description) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.description = description;   	
    }
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
		this.name = name;   
	}   
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
		this.description = description;
    }
    @Override
	public int hashCode() {
		return Objects.hash( description, name);
	}
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactDTO other = (ContactDTO) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
		&& Objects.equals(contactId, other.contactId);
    }
	@Override
	public String toString() {
		return  "Contact [contactId=" + contactId + ",name=" + name + ", description=" + description + "]";
	}
}
