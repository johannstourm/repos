package com.stourm.mtgappariement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.Username;
import com.stourm.mtgappariement.repos.UsernameRepository;

@Service
public class UsernameServiceImpl implements UsernameService {
	
	@Autowired
	UsernameRepository usernameRepository;
	
	public List<Username> getAllUserName() {
		return usernameRepository.findAll();
	};

	
	public List<Username> getUserNameById(Integer idUser) {
		return usernameRepository.getUserNameById(idUser);
	};

	public List<Username> getAllUsersByTournament(String nomTournoi) {
		return usernameRepository.getAllUsersByTournament(nomTournoi);
	};
	
	public List<Username> getUserNameByNameAndPseudo(Username username) {
		return usernameRepository.getUserNameByNameAndPseudo(username.getNom(), username.getPrenom(), username.getPseudo());
	};
	
	public Username putUsername(Username username) {
		return usernameRepository.save(username);
	}
	
}
