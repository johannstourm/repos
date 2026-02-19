package com.stourm.mtgappariement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.stourm.mtgappariement.entities.Tournoi;


public interface TournoiRepository extends JpaRepository<Tournoi,Integer> {

	@Query("select t from Tournoi t where idTournoi = :idTournoi")
	List<Tournoi> getTournoiById(@Param("idTournoi") Integer idTournoi);
	
	@Query("select t from Tournoi t where nomTournoi = :nomTournoi")
	List<Tournoi> getTournoiByName(@Param("nomTournoi") String nomTournoi);
	
}
