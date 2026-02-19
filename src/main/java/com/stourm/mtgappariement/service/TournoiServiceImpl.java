package com.stourm.mtgappariement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.repos.TournoiRepository;

@Service
public class TournoiServiceImpl implements TournoiService {
	
	@Autowired
	TournoiRepository tournoiRepository;
	
	public List<Tournoi> getTournoiById(Integer idTournoi	) {
		return tournoiRepository.getTournoiById(idTournoi);
	};

	public List<Tournoi> getTournoiByName(String nomTournoi	) {
		return tournoiRepository.getTournoiByName(nomTournoi);
	};
	
	public Tournoi putTournoi(Tournoi tournoi) {
		
		
		return tournoiRepository.save(tournoi);
	};
}
