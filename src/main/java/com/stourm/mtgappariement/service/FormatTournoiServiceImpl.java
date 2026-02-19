package com.stourm.mtgappariement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stourm.mtgappariement.entities.FormatTournoi;
import com.stourm.mtgappariement.repos.FormatTournoiRepository;

@Service
public class FormatTournoiServiceImpl implements FormatTournoiService {
	
	@Autowired
	FormatTournoiRepository formatTournoiRepository;
	
	public List<FormatTournoi> getFormatTournoiById(Integer idFormatTournoi	) {
		return formatTournoiRepository.getFormatTournoiById(idFormatTournoi);
	};

	public List<FormatTournoi> getFormatByName(String nomFormat){
		return formatTournoiRepository.getFormatByName(nomFormat);
	};
	
	public FormatTournoi putFormatTournoi(FormatTournoi formatTournoi) {
		return formatTournoiRepository.save(formatTournoi);
	}
}
