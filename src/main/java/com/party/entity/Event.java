package com.party.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;
	private String eventName;
	private String description;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String location;
	
	@ManyToMany(mappedBy = "events")
	private List<Users> users;
	
	public Event() {}
	public Event(int eventId, String eventName, String description, LocalDate date, LocalTime startTime,
			LocalTime endTime, String location) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.description = description;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	@Override
	public int hashCode() {
		return Objects.hash(date, description, endTime, eventId, eventName, location, startTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(endTime, other.endTime) && eventId == other.eventId
				&& Objects.equals(eventName, other.eventName) && Objects.equals(location, other.location)
				&& Objects.equals(startTime, other.startTime);
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", description=" + description + ", date="
				+ date + ", startTime=" + startTime + ", endTime=" + endTime + ", location=" + location + "]";
	}
	
	
}
