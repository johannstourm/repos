package com.stourm.mtgappariement.service;

import java.util.List;


import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.ResultatId;
import com.stourm.mtgappariement.entities.Tournoi;

public interface ResultatService {

	List<Resultat> getResultatById(ResultatId idResultat);
	
	List<Resultat> getResultatByTournoiAndRound(Tournoi tournoi, Integer noRonde);
	
	Resultat getResultatByUserTournoiAndRound(Integer user, Tournoi tournoi, Integer noRonde);
	
	void putResultat(Resultat resultat);
	
}
