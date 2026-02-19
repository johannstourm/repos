package com.stourm.mtgappariement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stourm.mtgappariement.entities.UserSecur;

public interface UserSecurRepository extends JpaRepository<UserSecur, Long> { 
	
	UserSecur findByUsername(String username); 
} 
