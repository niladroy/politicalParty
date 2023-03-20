package com.party.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int agendaId;
	private String description;
	public Agenda() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Agenda(int agendaId, String description) {
		super();
		this.agendaId = agendaId;
		this.description = description;
	}
	public int getAgendaId() {
		return agendaId;
	}
	public void setAgendaId(int agendaId) {
		this.agendaId = agendaId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		return Objects.hash(agendaId, description);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		return agendaId == other.agendaId && Objects.equals(description, other.description);
	}
	@Override
	public String toString() {
		return "Agenda [agendaId=" + agendaId + ", description=" + description + "]";
	}
	
	
}
