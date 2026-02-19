package com.stourm.mtgappariement.service;

import java.util.List;

import com.stourm.mtgappariement.entities.Username;

public interface UsernameService {
	
	List<Username> getAllUserName();
	
	List<Username> getUserNameById(Integer idUser);
	
	List<Username> getAllUsersByTournament(String nomTournoi);
	
	List<Username> getUserNameByNameAndPseudo(Username username);

	Username putUsername(Username username); 
}
