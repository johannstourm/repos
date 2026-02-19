package com.stourm.mtgappariement.service;

import java.util.List;

import com.stourm.mtgappariement.entities.RoleSecur;
import com.stourm.mtgappariement.entities.UserSecur;

public interface UserSecurService { 
	
	 UserSecur saveUser(UserSecur user); 
	 UserSecur findUserByUsername (String username); 
	 RoleSecur addRole(RoleSecur role); 
	 UserSecur addRoleToUser(String username, String rolename); 
	 
	 List<UserSecur> findAllUsers();
} 
