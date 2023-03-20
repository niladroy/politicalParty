package com.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
