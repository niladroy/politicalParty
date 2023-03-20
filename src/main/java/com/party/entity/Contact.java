package com.party.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity


public class Contact {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contact_id")
    private int contactId;
    private String name;
    private String description;

	@ManyToMany(mappedBy = "events")
	private List<Users> users;
	
    public Contact() {}
    public Contact(int contactId, String name , String description) {
        this.name = name;
        this.description = description;
		this.contactId = contactId;
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
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
    @Override
	public int hashCode() {
		return Objects.hash(description, name);
	}
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(name, other.name) && Objects.equals(contactId, other.contactId);
    }
    	@Override
	public String toString() {
		return  "Contact [contactId=" + contactId + ",name=" + name + ", description=" + description + "]";
	}

}
