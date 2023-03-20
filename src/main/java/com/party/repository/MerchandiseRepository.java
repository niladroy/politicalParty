package com.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.entity.Merchandise;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Integer>{

}
