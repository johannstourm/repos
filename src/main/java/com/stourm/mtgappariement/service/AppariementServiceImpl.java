package com.stourm.mtgappariement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;
import com.stourm.mtgappariement.repos.AppariementRepository;

@Service
public class AppariementServiceImpl implements AppariementService {
	
	@Autowired
	AppariementRepository appariementRepository;
	
	public List<Appariement> getAppariementById(Integer idAppariement) {
		return appariementRepository.getAppariementById(idAppariement);
	};

	public List<Appariement> findAppariementByUsers(Tournoi tournoi, Username user1, Username user2) {
		return appariementRepository.findAppariementByUsers(tournoi, user1, user2);
	};	

	public List<Appariement> findPlayerAffected(Tournoi tournoi, Username user, Integer noRonde) {
		return appariementRepository.findPlayerAffected(tournoi, user, noRonde);
	};
	
	public List<Appariement> findAppariementByTournament(Tournoi tournoi) {
		return appariementRepository.findAppariementByTournament(tournoi);
	};
	
	public List<Appariement> findAppariementByTournamentAndRound(Tournoi tournoi, Integer noRonde) {
		return appariementRepository.findAppariementByTournamentAndRound(tournoi, noRonde);
	};
	
	public void putAppariementbyTournoiAndRound(Appariement appariement) {
		appariementRepository.save(appariement);
	};
}
