package com.stourm.mtgappariement.utils;


import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.constantes.ConstantesAppariement;
import com.stourm.mtgappariement.constantes.ConstantesResultat;
import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.exceptions.InvalidNbRoundsException;
import com.stourm.mtgappariement.service.AppariementService;
import com.stourm.mtgappariement.service.ResultatService;

@Service
public class UtilsResultat {
	
	@Autowired
	AppariementService appariementService;
	
	@Autowired
	ResultatService resultatService;

	public boolean checkSaisieScore(Integer scorePlayer1, Integer scorePlayer2) throws InvalidNbRoundsException{
		
		if (ConstantesResultat.LST_NB_ROUND_WON.contains(scorePlayer1) && 
		ConstantesResultat.LST_NB_ROUND_WON.contains(scorePlayer2))
			 throw new InvalidNbRoundsException();
		
		return ConstantesResultat.LST_NB_ROUND_WON.contains(scorePlayer1) && 
						ConstantesResultat.LST_NB_ROUND_WON.contains(scorePlayer2) ? true : false;
	}
	
	
	public void createResultat(Tournoi tournoi) {
		
		int resultPlayer1, resultPlayer2, diffScore;
		int scorePlayer1, scorePlayer2;
		int goalAverage1, goalAverage2;
		
		Resultat resultatP1, resultatP2;

		
		List<Appariement> lstAppariement = appariementService.findAppariementByTournamentAndRound(tournoi, ConstantesResultat.FIRST_ROUND);
		
		for (Appariement appariement : lstAppariement) {
			
			resultPlayer1 = appariement.getResultat1();
			resultPlayer2 = appariement.getResultat2();
			diffScore = resultPlayer1 - resultPlayer2;
			
			goalAverage1 = diffScore;
			goalAverage2 = diffScore * (-1);
			
			switch(diffScore) {
			
				case 1,2:
					scorePlayer1 = ConstantesResultat.SCORE_ADD_WON;
					scorePlayer2 = ConstantesResultat.SCORE_ADD_LOST;
					break;
				
				case -2,-1:
					scorePlayer1 = ConstantesResultat.SCORE_ADD_LOST;
					scorePlayer2 = ConstantesResultat.SCORE_ADD_WON;
					break;
					
				case 0:
					scorePlayer1 = ConstantesResultat.SCORE_ADD_DRAW;
					scorePlayer2 = ConstantesResultat.SCORE_ADD_DRAW;
					break;
					
				default:
					scorePlayer1 = ConstantesResultat.SCORE_INITIAL;
					scorePlayer2 = ConstantesResultat.SCORE_INITIAL;
			}
			
			scorePlayer1 = appariement.getUser2().getPseudo().contains(ConstantesAppariement.USER_BAIL) ? ConstantesResultat.SCORE_ADD_WON : scorePlayer1;
			scorePlayer2 = appariement.getUser1().getPseudo().contains(ConstantesAppariement.USER_BAIL) ? ConstantesResultat.SCORE_ADD_WON : scorePlayer2;
			
			scorePlayer1 = appariement.getUser1().getPseudo().contains(ConstantesAppariement.USER_BAIL) ? ConstantesResultat.SCORE_ADD_LOST : scorePlayer1;
			scorePlayer2 = appariement.getUser2().getPseudo().contains(ConstantesAppariement.USER_BAIL) ? ConstantesResultat.SCORE_ADD_LOST : scorePlayer2;
			
			resultatP1 = new Resultat(appariement.getUser1(), appariement.getTournoi(), scorePlayer1,goalAverage1,0,appariement.getNoRonde());
			resultatService.putResultat(resultatP1);
			
			resultatP2 = new Resultat(appariement.getUser2(), appariement.getTournoi(), scorePlayer2,goalAverage2,0,appariement.getNoRonde());
			resultatService.putResultat(resultatP2);
		}
		
		setRangByResultAndRound(tournoi, ConstantesResultat.FIRST_ROUND);
		
	}
	
	
	public void setRangByResultAndRound(Tournoi tournoi, Integer noRonde) {
		
		List<Resultat> lstResultat = resultatService.getResultatByTournoiAndRound(tournoi, noRonde);
		
		List<Resultat> lstResultatSorted = lstResultat.stream()
														.sorted(Comparator.comparing(Resultat::getScore))
														.toList()
														.reversed();
		
		for (Resultat rss : lstResultatSorted) {
			rss.setRang(lstResultatSorted.indexOf(rss) + 1);
			resultatService.putResultat(rss);
		}
				
	}
	
	public void updateResultat(Tournoi tournoi,  Integer noRonde)  {
	
		int resultPlayer1, resultPlayer2, diffScore;
		int newScorePlayer1 = ConstantesResultat.SCORE_INITIAL, newScorePlayer2 = ConstantesResultat.SCORE_INITIAL;
		int newGoalAverage1, newGoalAverage2;
		
		Resultat resultatP1, resultatP2;
		
		
		List<Appariement> lstAppariement = appariementService.findAppariementByTournamentAndRound(tournoi, noRonde);
				
		for (Appariement appariement : lstAppariement) {
			
			resultPlayer1 = appariement.getResultat1();
			resultPlayer2 = appariement.getResultat2();
			diffScore = resultPlayer1 - resultPlayer2;
			
			resultatP1 = resultatService.getResultatByUserTournoiAndRound(appariement.getUser1().getIdUser(), tournoi, noRonde - 1);
			resultatP2 = resultatService.getResultatByUserTournoiAndRound(appariement.getUser2().getIdUser(), tournoi, noRonde - 1);
			
			newGoalAverage1 = diffScore;
			newGoalAverage2 = diffScore * (-1);
			
			switch(diffScore) {
			
			case 1,2:
				newScorePlayer1 = resultatP1.getScore() + ConstantesResultat.SCORE_ADD_WON;
				break;
			
			case -1,-2:
				newScorePlayer2 = resultatP2.getScore() + ConstantesResultat.SCORE_ADD_WON;
				break;
				
			case 0:
				newScorePlayer1 = resultatP1.getScore() + ConstantesResultat.SCORE_ADD_DRAW;
				newScorePlayer2 = resultatP2.getScore() + ConstantesResultat.SCORE_ADD_DRAW;
				break;
				
			default:
				newScorePlayer1 = resultatP1.getScore();
				newScorePlayer2 =  resultatP2.getScore();
		}
			
			
			if ( appariement.getUser2().getPseudo().contains(ConstantesAppariement.USER_BAIL) )
				newScorePlayer1 = resultatP1.getScore() + ConstantesResultat.SCORE_ADD_WON;
			
			if ( appariement.getUser1().getPseudo().contains(ConstantesAppariement.USER_BAIL) )
				newScorePlayer2 = resultatP2.getScore() + ConstantesResultat.SCORE_ADD_WON;
			
			
			
			resultatP1 = new Resultat(appariement.getUser1(), appariement.getTournoi(), newScorePlayer1,newGoalAverage1,ConstantesResultat.SCORE_INITIAL,noRonde);
			resultatService.putResultat(resultatP1);
			
			resultatP2 = new Resultat(appariement.getUser2(), appariement.getTournoi(), newScorePlayer2,newGoalAverage2,ConstantesResultat.SCORE_INITIAL,noRonde);
			resultatService.putResultat(resultatP2);
						
		}
		
		setRangByResultAndRound(tournoi, noRonde);
	}
}
