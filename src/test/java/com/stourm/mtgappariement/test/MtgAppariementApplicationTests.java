package com.stourm.mtgappariement.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.FormatTournoi;
import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.ResultatId;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;
import com.stourm.mtgappariement.service.AppariementService;
import com.stourm.mtgappariement.service.FormatTournoiService;
import com.stourm.mtgappariement.service.ResultatService;
import com.stourm.mtgappariement.service.TournoiService;
import com.stourm.mtgappariement.service.UsernameService;
import com.stourm.mtgappariement.utils.UtilsAppariement;

@SpringBootTest
class MtgAppariementApplicationTests {

	/*********************
	 *  - Services
	 *********************/
	
	@Autowired 
	private UsernameService usernameService; 
	
	@Autowired 
	private TournoiService tournoiService; 
	
	@Autowired 
	private FormatTournoiService formatTournoiService; 
	
	@Autowired 
	private AppariementService appariementService; 
	
	@Autowired 
	private ResultatService resultatService; 
	
	
	@Test
	void contextLoads() {
	}
	
	@Test 
	//@SuppressWarnings("unchecked")
	public void testUsername() { 
	
		// On veut récupérer le premier User de la table
		List<Username> mockListUsername = usernameService.getUserNameById(1);

		for(Username user: mockListUsername) {
			System.out.println("1 - Le premier User est: " + user.getNom() + " " + user.getPrenom());
		}
		
	}  
	
	@Test 
	public void testTournoi() { 
	
		// On veut récupérer le premier Tournoi de la table
		List<Tournoi> mockListTournoi = tournoiService.getTournoiById(1);

		for(Tournoi tournoi: mockListTournoi) {
			System.out.println("2 - Le premier Tournoi est: " + tournoi.getNomTournoi());
		}
		
	}  
	
	@Test 
	public void testFormatTournoi() { 
	
		// On veut récupérer le premier Format de tournoi de la table
		List<FormatTournoi> mockListFormatTournoi = formatTournoiService.getFormatTournoiById(1);

		for(FormatTournoi formatTournoi: mockListFormatTournoi) {
			System.out.println("3 - Le premier format de tournoi est: " + formatTournoi.getNomFormat());
		}
		
	}  

	
	@Test 
	public void testAppariement() { 
	
		// On veut récupérer le premier Appariement de la table
		List<Appariement> mockListAppariement = appariementService.getAppariementById(1);

		for(Appariement appariement: mockListAppariement) {
			System.out.println("4 - Le premier appariement est: N° Ronde" + appariement.getNoRonde() + 
									" avec " + appariement.getUser1() + 
									" contre " + appariement.getUser2());
		}
		
	}  
	
	
	@Test 
	public void testResultat() { 
	
		// On veut récupérer le premier Resultat de la table
		Username mockUsername = usernameService.getUserNameById(1).getFirst();
		Tournoi mockTournoi = tournoiService.getTournoiById(1).getFirst();
		ResultatId resultatId = new ResultatId(mockUsername, mockTournoi);
		List<Resultat> mockListResultat = resultatService.getResultatById(resultatId);

		for(Resultat resultat: mockListResultat) {
			System.out.println("5 - Le premier resultat est: - Tournoi :" + resultat.getTournoi().getNomTournoi() +  
									" - Joueur : " + resultat.getUser().getPseudo() +
									" avec un score de " + resultat.getScore());
		}
	}
		
	@Test 
	public void testGetAllUsersByTournoi() { 
		
			// On veut récupérer la liste des user avec la premier Tournoi 
			List<Username> mockListUserName = usernameService.getAllUsersByTournament("Tournoi_Test");

			System.out.println("6 - La liste des joueurs du premier tournoi est: ");
			for(Username username: mockListUserName) {			
				System.out.println(" " + username.getPseudo() + " ");
			}
		
	}
	

}

