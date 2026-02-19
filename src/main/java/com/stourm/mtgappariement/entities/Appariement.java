package com.stourm.mtgappariement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appariement")
public class Appariement {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Integer idAppariement;
	private Integer noRonde;
	
	// Resultat de la manche et non resultat total sur le tournoi 
	private Integer resultat1;
	private Integer resultat2;
	
	@ManyToOne @JoinColumn(name="idTournoi", nullable=false)
	private Tournoi tournoi;
	
	@ManyToOne @JoinColumn(name="idUser1", nullable=false)
	private Username user1;
	

	@ManyToOne @JoinColumn(name="idUser2", nullable=false)
	private Username user2;
	
	
	public Appariement() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Appariement(Integer idAppariement, Integer idTournoi, Integer idUser1, Integer idUser2, Integer noRonde,
			Integer resultat1, Integer resultat2) {
		super();
		this.idAppariement = idAppariement;
		this.noRonde = noRonde;
		this.resultat1 = resultat1;
		this.resultat2 = resultat2;
	}



	public Integer getIdAppariement() {
		return idAppariement;
	}



	public void setIdAppariement(Integer idAppariement) {
		this.idAppariement = idAppariement;
	}



	public Integer getNoRonde() {
		return noRonde;
	}



	public void setNoRonde(Integer noRonde) {
		this.noRonde = noRonde;
	}



	public Integer getResultat1() {
		return resultat1;
	}



	public void setResultat1(Integer resultat1) {
		this.resultat1 = resultat1;
	}



	public Integer getResultat2() {
		return resultat2;
	}


	public void setResultat2(Integer resultat2) {
		this.resultat2 = resultat2;
	}
	
	public Username getUser1() {
		return user1;
	}


	public void setUser1(Username user1) {
		this.user1 = user1;
	}


	public Username getUser2() {
		return user2;
	}


	public void setUser2(Username user2) {
		this.user2 = user2;
	}



	public Tournoi getTournoi() {
		return tournoi;
	}



	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
	
	
	
}
