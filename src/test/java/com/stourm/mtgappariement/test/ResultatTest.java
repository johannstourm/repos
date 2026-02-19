package com.stourm.mtgappariement.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.utils.UtilsResultat;

@SpringBootTest
class ResultatTest {
	
	/*********************
	 *  - Utils
	 *********************/
	
	
	@Autowired 
	private UtilsResultat resultat; 

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	/******************************************
	 *  - Resultat
	 ******************************************/

	@Test 
	public void testSetRangByResultAndRound() { 
		
		Tournoi tournoi = new Tournoi(1,1,"Tournoi_Test",null); 
		
		System.out.println("1 - Resultat - Test SortedList: ");
		resultat.setRangByResultAndRound(tournoi,1);
			
//		for(Resultat rs : lstResultat) {
//			System.out.println("* User: " + rs.getUser() + " Score: " + rs.getScore() + " Rang: " + rs.getRound());
//		}
			
		
	}
	
	
}
