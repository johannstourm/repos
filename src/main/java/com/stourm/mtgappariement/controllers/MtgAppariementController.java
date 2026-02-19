package com.stourm.mtgappariement.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stourm.mtgappariement.constantes.ConstantesResultat;
import com.stourm.mtgappariement.dto.AppariementInDTO;
import com.stourm.mtgappariement.dto.AppariementOutDTO;
import com.stourm.mtgappariement.dto.FormatTournoiDTO;
import com.stourm.mtgappariement.dto.ResultatInDTO;
import com.stourm.mtgappariement.dto.ResultatOutDTO;
import com.stourm.mtgappariement.dto.ScoreInDTO;
import com.stourm.mtgappariement.dto.TournoiInDTO;
import com.stourm.mtgappariement.dto.TournoiOutDTO;
import com.stourm.mtgappariement.dto.UsernameInDTO;
import com.stourm.mtgappariement.dto.UsernameOutDTO;
import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.FormatTournoi;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;
import com.stourm.mtgappariement.exceptions.InvalidNbRoundsException;
import com.stourm.mtgappariement.service.AppariementService;
import com.stourm.mtgappariement.service.FormatTournoiService;
import com.stourm.mtgappariement.service.TournoiService;
import com.stourm.mtgappariement.service.UsernameService;
import com.stourm.mtgappariement.utils.UtilsAppariement;
import com.stourm.mtgappariement.utils.UtilsResultat;
import com.stourm.mtgappariement.utils.UtilsRound;


@RestController
@RequestMapping("/mtg")
@CrossOrigin(origins = "*")
public class MtgAppariementController {

	@Autowired
	AppariementService appariementService;
	
	@Autowired
	UtilsAppariement utilAppariement;
	
	@Autowired
	UsernameService usernameService;

	@Autowired
	FormatTournoiService formatTournoiService;
	
	@Autowired
	TournoiService tournoiService;
	
	@Autowired 
	UtilsResultat utilsResultat;
	
	@Autowired 
	UtilsRound utilsRound;
	
	@GetMapping("/user") 
	public UsernameOutDTO getAllUser() { 
		
		UsernameOutDTO usernameOutDTO = new UsernameOutDTO(usernameService.getUserNameById(1));
		return usernameOutDTO; 
	}   
	
	
	@PostMapping("/player")
	public boolean createUser(@RequestBody UsernameInDTO usernameInDTO) {
		

		List<Username> userNameExist;
		try {
			userNameExist = usernameService.getUserNameByNameAndPseudo(usernameInDTO.user());
		
			if (userNameExist.isEmpty()) {
				usernameService.putUsername(usernameInDTO.user());
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			System.out.println("Les users ne sont pas recupérables");
			return false;
		}

	}
	
	
	
	@PostMapping("/format")
	public boolean createFormat(@RequestBody FormatTournoiDTO formatTournoiDTO) {
		
		List<FormatTournoi> FormatTournoiExist; 
		try {
			FormatTournoiExist = formatTournoiService.getFormatByName(formatTournoiDTO.formatTournoi().getNomFormat());
			
			if (FormatTournoiExist.isEmpty()) {
				formatTournoiService.putFormatTournoi(formatTournoiDTO.formatTournoi());
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			System.out.println("Les formats de tournois ne sont pas recupérables");
			return false;
		}
	}


	@PostMapping("/valid")
	public Tournoi createTournament(@RequestBody TournoiInDTO tournoiInDTO) {  // (List<Username> lstUser, Tournoi tournoi) {
		
			Tournoi tournoi = tournoiInDTO.tournoi();
			tournoi.setTournois(tournoiInDTO.lstUser());
			
			List<Tournoi> tournoiExist;
			
			try {
				tournoiExist = tournoiService.getTournoiByName(tournoiInDTO.tournoi().getNomTournoi());
				if (tournoiExist.isEmpty()) {
					for (Username username: tournoi.getTournois()) {
						usernameService.putUsername(username);
					}
				tournoiService.putTournoi(tournoi);
				return tournoi;
				} 
			} catch (NoSuchElementException e) {
				System.out.println("Le tournoi n existe pas !!");
				return null;
			}
			return null;
	}
	
	
	@PostMapping("/generation")
	public boolean createAppariement(@RequestBody AppariementInDTO appariementInDTO)  { //(Tournoi tournoi, Integer noRonde) {
		
		List<Appariement> lstAppariement;		
		
		if (appariementInDTO.noRonde() == ConstantesResultat.FIRST_ROUND ) {
			lstAppariement= utilAppariement.chooseRandom(appariementInDTO.tournoi().getNomTournoi());
			for(Appariement appariement : lstAppariement) {
				appariementService.putAppariementbyTournoiAndRound(appariement);
			}
		}
		else {
			lstAppariement= utilAppariement.calculAppariement(appariementInDTO.tournoi(), appariementInDTO.noRonde());
		}
		
		
		
		return true;
	}
	
	@PostMapping("/score")
	public boolean SetScoreByAppariement(@RequestBody ScoreInDTO scoreInDTO	) {
		
		Appariement appariement = appariementService.getAppariementById(scoreInDTO.idAppariement()).getLast();
		appariement.setResultat1(scoreInDTO.nbRoundPlayer1());
		appariement.setResultat2(scoreInDTO.nbRoundPlayer2());
		appariementService.putAppariementbyTournoiAndRound(appariement);
		
		return true;
	}
	
	@PostMapping("/resultat")
	public boolean SetResultatByAppariement(@RequestBody ResultatInDTO resultatInDTO) {
		
		List<Appariement> lstAppariement = appariementService.findAppariementByTournamentAndRound(resultatInDTO.tournoi(), resultatInDTO.noRonde());
		
		boolean check = true;
		
		try {
				for (Appariement appariement : lstAppariement) {
					if(!utilsResultat.checkSaisieScore(appariement.getResultat1(), appariement.getResultat2()))
						check = false;
				} 
		} catch(InvalidNbRoundsException inre) {
					System.out.println("Saisie des nombres de manches gagnantes incorrectes");
				}
		
		if(check) {
			
			if (resultatInDTO.noRonde() == ConstantesResultat.FIRST_ROUND ) 
					utilsResultat.createResultat(resultatInDTO.tournoi()); 
			else
					utilsResultat.updateResultat(resultatInDTO.tournoi(), resultatInDTO.noRonde());
		}
		
		return check;
	}
	
	@GetMapping("/tournament")
	public TournoiOutDTO getTournament(@RequestBody TournoiInDTO tournoiInDTO) {
		
		return new TournoiOutDTO(utilsRound.loadTournament( tournoiInDTO.tournoi().getNomTournoi()).getLast());
	}
	
	@GetMapping("/appariement")
	public AppariementOutDTO getAppariement (@RequestBody AppariementInDTO appariementInDTO) {
		
		return new AppariementOutDTO(utilsRound.loadAppariement(appariementInDTO.tournoi(), appariementInDTO.noRonde()).getLast());
	}
	
	@GetMapping("/resultat")
	public ResultatOutDTO getResultat (@RequestBody ResultatInDTO resultatInDTO) {
		
		return new  ResultatOutDTO(utilsRound.loadResultat(resultatInDTO.tournoi(), resultatInDTO.noRonde()).getLast());
	}
	
}
