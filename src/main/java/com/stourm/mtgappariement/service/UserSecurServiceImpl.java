package com.stourm.mtgappariement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.RoleSecur;
import com.stourm.mtgappariement.entities.UserSecur;
import com.stourm.mtgappariement.repos.RoleSecurRepository;
import com.stourm.mtgappariement.repos.UserSecurRepository;

import jakarta.transaction.Transactional;

@Transactional 
@Service 
public class UserSecurServiceImpl implements UserSecurService {

	@Autowired 
	 UserSecurRepository userRep; 
	  
	 @Autowired 
	 RoleSecurRepository roleRep; 
	  
	  
	 @Autowired 
	 BCryptPasswordEncoder bCryptPasswordEncoder; 
	  
	 @Override 
	 public UserSecur saveUser(UserSecur user) { 
	   
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); 
	  return userRep.save(user); 
	 } 
	 
	 @Override 
	 public UserSecur addRoleToUser(String username, String rolename) { 
	  UserSecur usr = userRep.findByUsername(username); 
	  RoleSecur r = roleRep.findByRole(rolename); 
	   
	  usr.getRoles().add(r); 
	  return usr; 
	 } 
	 
	  
	 @Override 
	 public RoleSecur addRole(RoleSecur role) { 
	  return roleRep.save(role); 
	 } 
	 
	 @Override 
	 public UserSecur findUserByUsername(String username) {  
	  return userRep.findByUsername(username); 
	 } 

	 @Override 
	 public List<UserSecur> findAllUsers() { 
		 return userRep.findAll(); 
	 }

}
