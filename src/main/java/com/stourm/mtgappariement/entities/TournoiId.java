package com.stourm.mtgappariement.entities;

public class TournoiId {

	private Integer idTournoi;
	private Integer idFormat;

	
	public TournoiId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TournoiId(Integer idTournoi, Integer idFormat, Integer idUser) {
		super();
		this.idTournoi = idTournoi;
		this.idFormat = idFormat;

	}

	public Integer getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(Integer idTournoi) {
		this.idTournoi = idTournoi;
	}

	public Integer getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(Integer idFormat) {
		this.idFormat = idFormat;
	}

//	public Integer getIdUser() {
//		return idUser;
//	}
//
//	public void setIdUser(Integer idUser) {
//		this.idUser = idUser;
//	}

	
}
