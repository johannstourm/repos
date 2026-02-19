package com.stourm.mtgappariement.service;

import java.util.List;

import com.stourm.mtgappariement.entities.FormatTournoi;

public interface FormatTournoiService {
	
	List<FormatTournoi> getFormatTournoiById(Integer idFormat);

	List<FormatTournoi> getFormatByName(String nomFormat);
	
	FormatTournoi putFormatTournoi(FormatTournoi formatTournoi);
}
