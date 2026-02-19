package com.stourm.mtgappariement.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Format_Tournoi")
public class FormatTournoi {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFormat;
	private String nomFormat;
	private Integer nbCartes;
	private String commentaires;
	
	@OneToMany (targetEntity=Tournoi.class, mappedBy="formatTournoi")
	private List<Tournoi> tournois = new ArrayList<>();
	
	public FormatTournoi() {
		super();
		// TODO Auto-generated constructor stub
	}



	public FormatTournoi(Integer idFormat, String nomFormat, Integer nbCartes, String commentaires,
			List<Tournoi> tournois) {
		super();
		this.idFormat = idFormat;
		this.nomFormat = nomFormat;
		this.nbCartes = nbCartes;
		this.commentaires = commentaires;
		this.tournois = tournois;
	}



	public Integer getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(Integer idFormat) {
		this.idFormat = idFormat;
	}

	public String getNomFormat() {
		return nomFormat;
	}

	public void setNomFormat(String nomFormat) {
		this.nomFormat = nomFormat;
	}

	public Integer getNbCartes() {
		return nbCartes;
	}

	public void setNbCartes(Integer nbCartes) {
		this.nbCartes = nbCartes;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public void setTournois(List<Tournoi> tournois) {
		this.tournois = tournois;
	}	
	
}
