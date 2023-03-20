package com.party.dto;

import java.util.Objects;


public class AgendaDTO {
	private int agendaId;
	private String description;
	
	public AgendaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgendaDTO(int agendaId, String description) {
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
		AgendaDTO other = (AgendaDTO) obj;
		return agendaId == other.agendaId && Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "Agenda [agendaId=" + agendaId + ", description=" + description + "]";
	}
}
