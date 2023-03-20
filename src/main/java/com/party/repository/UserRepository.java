package com.party.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUserName(String userName);
}
