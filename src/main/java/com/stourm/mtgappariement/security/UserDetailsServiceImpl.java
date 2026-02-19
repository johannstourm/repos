package com.stourm.mtgappariement.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.UserSecur;
import com.stourm.mtgappariement.service.UserSecurService;


	@Service 
	public class UserDetailsServiceImpl implements UserDetailsService { 
		
	 @Autowired 
	 UserSecurService userService; 
	  
	@Override 
	public UserDetails loadUserByUsername(String username) throws 
	UsernameNotFoundException { 
		
	 UserSecur user = userService.findUserByUsername(username); 
	  
	if (user==null)  
	    throw new UsernameNotFoundException("Utilisateur introuvable !"); 
	  
		 List<GrantedAuthority> auths = new ArrayList<>(); 
	  
	  user.getRoles().forEach(role -> { 
	   GrantedAuthority auhority = new 
	SimpleGrantedAuthority(role.getRole()); 
	   auths.add(auhority); 
	  }); 
	  
	 return new org.springframework.security.core. 
	   userdetails.User(user.getUsername(),user.getPassword(),auths); 
	  } 
	}
	

