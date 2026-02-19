package com.stourm.mtgappariement.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;
import com.stourm.mtgappariement.utils.UtilsAppariement;

@SpringBootTest
class AppariementTest {
	
	/*********************
	 *  - Utils
	 *********************/
	
	
	@Autowired 
	private UtilsAppariement appariement; 

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	/******************************************
	 *  - Appariements
	 ******************************************/

	@Test 
	public void testChooseRandom() { 
		
		System.out.println("1 - Appariement - Choose Random: ");
			appariement.chooseRandom("Tournoi_Test");
		
	}
	
	@Test
	public void testIsPlayerAppariementExistant() {
		
		System.out.println("2 - Appariement - Appariement existant: ");
		
		Username user1 = new Username(1, "BEBLO","GEOFFREY","JOJO");
		Username user2 = new Username(2, "CARTON","FRANCK","FRANCK");
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null);
		
		boolean isAppariementExistant = appariement.checkAppariementExistant(tournoi, user1, user2);
		System.out.println("La presence d'appariement est " + isAppariementExistant);
	}
	
	@Test
	public void testIsNotPlayerAppariementExistant() {
		
		System.out.println("3 - Appariement - Appariement inexistant: ");
		
		Username user1 = new Username(1, "BEBLO","GEOFFREY","JOJO");
		Username user2 = new Username(3, "BEBLO","BENOIT","GROBEN");
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null);
		
		boolean isAppariementExistant = appariement.checkAppariementExistant(tournoi, user1, user2);
		System.out.println("La presence d'appariement est " + isAppariementExistant);
	}
	
	@Test
	public void testIsPlayer1Tournoi1Affected() {
		
		System.out.println("4 - Appariement - Affectation user1: ");
		
		Username user = new Username(1, "BEBLO","GEOFFREY","JOJO");
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null);
		
		boolean isAppariementExistant = appariement.checkPlayerAffected(tournoi, user, 1);
		System.out.println("La presence d'appariement est " + isAppariementExistant);
		
	}
	
	@Test
	public void testIsPlayer2Tournoi1Affected() {
		
		System.out.println("4 - Appariement - Affectation user2: ");
		
		Username user = new Username(2, "CARTON","FRANCK","FRANCK");
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null);
		
		boolean isAppariementExistant = appariement.checkPlayerAffected(tournoi, user, 1);
		System.out.println("La presence d'appariement est " + isAppariementExistant);
		
	}
	
	@Test
	public void testIsPlayer1Tournoi2Affected() {
		
		System.out.println("5 - Appariement - Affectation tournoi inexistant: ");
		
		Username user = new Username(1, "BEBLO","GEOFFREY","JOJO");
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null);
		
		boolean isAppariementExistant = appariement.checkPlayerAffected(tournoi, user, 2);
		System.out.println("La presence d'appariement est " + isAppariementExistant);
		
	}
	
	@Test
	public void testCalculAppariement() {
		
		System.out.println("6 - Appariement - Calcul et Insertion de l'appariement de la 2nde ronde: ");
		
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null);
		
		appariement.calculAppariement(tournoi, 2);
	}
}
