package com.stourm.mtgappariement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stourm.mtgappariement.entities.Resultat;
import com.stourm.mtgappariement.entities.ResultatId;
import com.stourm.mtgappariement.entities.Tournoi;

public interface ResultatRepository extends JpaRepository<Resultat, ResultatId> {

	@Query("select r from Resultat r where user.idUser = :idUser and tournoi.idTournoi = :idTournoi")
	List<Resultat> getResultatById(@Param("idUser") Integer idUser, @Param("idTournoi")  Integer idTournoi);
	
	@Query("select r from Resultat r where r.tournoi = :tournoi and r.round = :noRonde order by r.rang")
	List<Resultat> getResultatByTournoiAndRound(@Param("tournoi") Tournoi tournoi, @Param("noRonde")  Integer noRonde);
	
	@Query("select r from Resultat r where user.idUser= :idUser and r.tournoi = :tournoi and r.round = :noRonde")
	Resultat getResultatByUserTournoiAndRound(@Param("idUser") Integer user, @Param("tournoi") Tournoi tournoi, @Param("noRonde")  Integer noRonde);
	
}
