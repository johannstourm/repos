package com.stourm.mtgappariement.service;

import java.util.List;

import com.stourm.mtgappariement.entities.Tournoi;

public interface TournoiService {
	
	List<Tournoi> getTournoiById(Integer idTournoi);

	List<Tournoi> getTournoiByName(String nomTournoi);
	
	Tournoi putTournoi(Tournoi tournoi);
}
