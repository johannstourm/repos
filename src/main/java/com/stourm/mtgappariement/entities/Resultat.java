package com.stourm.mtgappariement.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(ResultatId.class)
@Table(name="resultat")
public class Resultat {
	
	@Id
	@OneToOne @JoinColumn (name="idUser")
	private Username user;
	
	@Id
	@OneToOne @JoinColumn (name="idTournoi")
	private Tournoi tournoi;
	
	private Integer score;
	private Integer goalAverage;
	private Integer rang;
	
	@Id
	private Integer round;
	
	
	public Resultat() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Username getUser() {
		return user;
	}



	public void setUser(Username user) {
		this.user = user;
	}


	public Tournoi getTournoi() {
		return tournoi;
	}


	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}



	public Resultat(Username user, Tournoi tournoi, Integer score, Integer goalAverage, Integer rang, Integer round) {
		super();
		this.user = user;
		this.tournoi = tournoi;
		this.score = score;
		this.goalAverage = goalAverage;
		this.rang = rang;
		this.round = round;

	}


	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	

	public Integer getGoalAverage() {
		return goalAverage;
	}



	public void setGoalAverage(Integer goalAverage) {
		this.goalAverage = goalAverage;
	}



	public Integer getRang() {
		return rang;
	}

	public void setRang(Integer rang) {
		this.rang = rang;
	}
	
	public Integer getRound() {
		return round;
	}


	public void setRound(Integer round) {
		this.round = round;
	}

	
}
