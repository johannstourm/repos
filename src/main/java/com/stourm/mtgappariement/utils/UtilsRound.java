package com.stourm.mtgappariement.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.service.AppariementService;
import com.stourm.mtgappariement.service.ResultatService;
import com.stourm.mtgappariement.service.TournoiService;

@Service
public class UtilsRound {

	@Autowired
	AppariementService appariementService;
	
	@Autowired
	ResultatService resultatService;
	
	@Autowired
	TournoiService tournoiService;
	
	public List<Appariement> loadAppariement(Tournoi tournoi, Integer noRonde) {
		
		return appariementService.findAppariementByTournamentAndRound(tournoi, noRonde);
	}


	public List<Resultat> loadResultat(Tournoi tournoi, Integer noRonde) {
		
		return resultatService.getResultatByTournoiAndRound(tournoi, noRonde);
	}

	public List<Tournoi> loadTournament(String nomTournoi) {
		
		return tournoiService.getTournoiByName(nomTournoi);
	}
	
	
}
