package com.stourm.mtgappariement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.ResultatId;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.repos.ResultatRepository;

@Service
public class ResultatServiceImpl implements ResultatService {
	
	@Autowired
	ResultatRepository resultatRepository;
	
	public List<Resultat> getResultatById(ResultatId idResultat) {
			
		return resultatRepository.getResultatById(idResultat.getUser().getIdUser(), idResultat.getTournoi().getIdTournoi());
	};

	public List<Resultat> getResultatByTournoiAndRound(Tournoi tournoi, Integer noRonde) {
		
		return resultatRepository.getResultatByTournoiAndRound(tournoi, noRonde);
	};
	
	
	public Resultat getResultatByUserTournoiAndRound(Integer user, Tournoi tournoi, Integer noRonde) {
		return resultatRepository.getResultatByUserTournoiAndRound(user,tournoi, noRonde);
	};
	
	public void putResultat(Resultat resultat) {
		
		resultatRepository.save(resultat);
	};
	
}
