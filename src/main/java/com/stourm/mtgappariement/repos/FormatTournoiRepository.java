package com.stourm.mtgappariement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stourm.mtgappariement.entities.FormatTournoi;
import com.stourm.mtgappariement.entities.Username;

public interface FormatTournoiRepository extends JpaRepository<FormatTournoi, Integer> {

	@Query("select ft from FormatTournoi ft where idFormat = :idFormat")
	List<FormatTournoi> getFormatTournoiById(@Param("idFormat") Integer idFormat);
	
	@Query("select ft from FormatTournoi ft where nomFormat = :nomFormat")
	List<FormatTournoi> getFormatByName(@Param("nomFormat") String nomFormat);
	
}
