package com.stourm.mtgappariement.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.constantes.ConstantesAppariement;
import com.stourm.mtgappariement.constantes.ConstantesResultat;
import com.stourm.mtgappariement.constantes.ConstantesRound;
import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;
import com.stourm.mtgappariement.service.AppariementService;
import com.stourm.mtgappariement.service.ResultatService;
import com.stourm.mtgappariement.service.TournoiService;
import com.stourm.mtgappariement.service.UsernameService;

@Service
public class UtilsAppariement {
	
	@Autowired
	UsernameService usernameService;
	
	@Autowired
	AppariementService appariementService;
	
	@Autowired
	ResultatService resultatService;
	
	@Autowired
	TournoiService tournoiService;
	
	public List<Appariement> chooseRandom(String nomTournoi) {
	
		List<Username> listeJoueurs = usernameService.getAllUsersByTournament(nomTournoi);
		List<Username> sortedListJoueurs = new ArrayList<Username>();
		Tournoi tournoi = tournoiService.getTournoiByName(nomTournoi).getLast();
		
		Map<Integer, Username> randomJoueur = listeJoueurs.stream()
												.collect(Collectors.toMap(						
														u -> new Random().nextInt(),
														u -> u 
														 )
														); 
		
		List<Map.Entry<Integer, Username>> randomListeJoueurs = new ArrayList<>(randomJoueur.entrySet());
		randomListeJoueurs.sort(Map.Entry.comparingByKey());
																
		randomJoueur.forEach((key, value) -> System.out.println( key  + "->" + value.getPseudo()));
		System.out.print("---\n");
		for(Map.Entry<Integer, Username> entry : randomListeJoueurs) {
			System.out.println( entry.getKey() + " -> " + entry.getValue().getPseudo());
			sortedListJoueurs.add(entry.getValue());
		}
		
		return splitListUser(sortedListJoueurs, tournoi);
	}
	
	
	public List<Appariement> splitListUser(List<Username> lstUsername, Tournoi tournoi) {
		
		List<Appariement> lstAppariement = new ArrayList<Appariement>();
		Appariement appariement= new Appariement();
		Username userBail = new Username(0,ConstantesAppariement.USER_BAIL,ConstantesAppariement.USER_BAIL,ConstantesAppariement.USER_BAIL);
		
		for(Username username : lstUsername) {
			
			if (lstUsername.indexOf(username) % ConstantesAppariement.NB_PLAYERS_BY_MEET == 0) {
				appariement = new Appariement();
				
				appariement.setTournoi(tournoi);
				appariement.setUser1(username);
				appariement.setNoRonde(ConstantesRound.FIRST_ROUND);
				appariement.setResultat1(ConstantesResultat.SCORE_INITIAL);
				appariement.setResultat2(ConstantesResultat.SCORE_INITIAL);
				
				if ( lstUsername.indexOf(username) == lstUsername.size() - 1 ) {
					appariement.setUser2(userBail);
				lstAppariement.add(appariement);
				}
			}
			else {
				appariement.setUser2(username);
				lstAppariement.add(appariement);
			}
				
		}
		
		return lstAppariement;
	}
	
	public boolean checkAppariementExistant(Tournoi tournoi, Username user1, Username user2) {
		
		List<Appariement> lstAppariementExistant = appariementService.findAppariementByUsers(tournoi, user1, user2);		
		return !lstAppariementExistant.isEmpty();
	}
	
	
	public boolean checkPlayerAffected(Tournoi tournoi, Username user, Integer noRonde) {
		
		List<Appariement> lstAppariementPlayerAffected = appariementService.findPlayerAffected(tournoi, user, noRonde);		
		return !lstAppariementPlayerAffected.isEmpty();
	}
		
	public boolean isResultatContainsBail(Tournoi tournoi) {
		
		int nbPlayers = tournoi.getTournois().size();
		
			return nbPlayers % ConstantesAppariement.NB_PLAYERS_BY_MEET != 0 ? true: false;
		
	}
	
	public boolean isLastResultat(List<Resultat> lstResultat, Resultat resultat ) {
		
			return resultat == lstResultat.getLast() ? true : false;
	}
	
	
	public List<Appariement> calculAppariement(Tournoi tournoi, Integer noRonde) {
		
		boolean isPlayerAffected;
		boolean isAdverseAffected;
		boolean isAdverseMet;
		boolean isFindAdverse;
		
		List<Appariement> lstAppariement = new ArrayList<Appariement>();
		
		Appariement appariement;
		
		if ( noRonde > ConstantesResultat.FIRST_ROUND) {		
			
			List<Resultat> lstResultat =  resultatService.getResultatByTournoiAndRound(tournoi, noRonde - 1);	
			
			for(Resultat resultat : lstResultat) {
				
				System.out.println("Le joueur en cours est " + resultat.getUser().getPseudo());
				isPlayerAffected = checkPlayerAffected(tournoi, resultat.getUser(), noRonde);
				isFindAdverse = false;
				
				if (!isPlayerAffected) {
					
					for(Resultat resultatAdverse : lstResultat) {
						
						isAdverseAffected = checkPlayerAffected(tournoi, resultatAdverse.getUser(), noRonde);
						isAdverseMet = checkAppariementExistant(tournoi, resultat.getUser(), resultatAdverse.getUser());
						
						if (resultat.getUser() != resultatAdverse.getUser() && !isAdverseAffected && !isAdverseMet && !isFindAdverse) {
							
							appariement = new Appariement();
							appariement.setTournoi(tournoi);
							appariement.setUser1(resultat.getUser());
							appariement.setUser2(resultatAdverse.getUser());
							appariement.setNoRonde(noRonde);
							appariement.setResultat1(0);
							appariement.setResultat2(0);
								
							isFindAdverse = true;
						   System.out.println("Le cle genere est " + appariement.getIdAppariement());
						   appariementService.putAppariementbyTournoiAndRound(appariement);
						}
						
						
					}
					
				}				
			}
		}
		
		return lstAppariement;
	}
}

