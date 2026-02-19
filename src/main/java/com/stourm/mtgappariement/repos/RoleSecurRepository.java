package com.stourm.mtgappariement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stourm.mtgappariement.entities.RoleSecur; 

 
public interface RoleSecurRepository extends JpaRepository<RoleSecur, Long> { 
 
 RoleSecur findByRole(String role); 
  
} 
 