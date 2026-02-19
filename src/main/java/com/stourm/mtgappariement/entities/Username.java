package com.stourm.mtgappariement.entities;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="username")
public class Username {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUser;
	private String nom;
	private String prenom;
	private String pseudo;
	
	
	public Username() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Username(Integer idUser, String nom, String prenom, String pseudo) {
		super();
		this.idUser = idUser;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
	}


	public Integer getIdUser() {
		return idUser;
	}


	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (name="ass_tournoi_user", 
				joinColumns = @JoinColumn (name= "ID_USER"),
				inverseJoinColumns = @JoinColumn(  name = "ID_TOURNOI")
				)
	private List<Tournoi> tournois = new ArrayList<>();


	public List<Tournoi> getTournois() {
		return tournois;
	}


	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}
	
	
}
