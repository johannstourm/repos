package com.stourm.mtgappariement.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="tournoi")
public class Tournoi {

	@Id
	private Integer idTournoi;
	private String nomTournoi;

	private Integer idUser;
	
	@ManyToMany
	@JoinTable (name="ass_tournoi_user", 
				joinColumns = @JoinColumn (name= "ID_TOURNOI"),
				inverseJoinColumns = @JoinColumn(  name = "ID_USER")
				)
	private List<Username> tournois = new ArrayList<>();
	
	@ManyToOne @JoinColumn(name="idFormat", nullable=false)
	private FormatTournoi formatTournoi;
	
	public Tournoi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tournoi(Integer idTournoi, Integer idFormat, String nomTournoi, Integer idUser) {
		super();
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.idUser = idUser;
	}

	public Integer getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(Integer idTournoi) {
		this.idTournoi = idTournoi;
	}


	public String getNomTournoi() {
		return nomTournoi;
	}

	public void setNomTournoi(String nomTournoi) {
		this.nomTournoi = nomTournoi;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public List<Username> getTournois() {
		return tournois;
	}

	public void setTournois(List<Username> tournois) {
		this.tournois = tournois;
	}

	public FormatTournoi getFormatTournoi() {
		return formatTournoi;
	}

	public void setFormatTournoi(FormatTournoi formatTournoi) {
		this.formatTournoi = formatTournoi;
	}
}
