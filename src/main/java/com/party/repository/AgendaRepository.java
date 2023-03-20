package com.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

}
