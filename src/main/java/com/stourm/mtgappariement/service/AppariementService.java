package com.stourm.mtgappariement.service;

import java.util.List;


import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;

public interface AppariementService {
	
	List<Appariement> getAppariementById(Integer idAppariement);

	List<Appariement> findAppariementByUsers(Tournoi tournoi, Username user1,  Username user2);	
	
	List<Appariement> findPlayerAffected(Tournoi tournoi, Username user, Integer noRonde);
	
	List<Appariement> findAppariementByTournament(Tournoi tournoi);
	
	List<Appariement> findAppariementByTournamentAndRound(Tournoi tournoi, Integer noRonde);
	
	void putAppariementbyTournoiAndRound(Appariement appariement);

}
