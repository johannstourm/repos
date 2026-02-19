package com.stourm.mtgappariement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stourm.mtgappariement.entities.Appariement;
import com.stourm.mtgappariement.entities.Tournoi;
import com.stourm.mtgappariement.entities.Username;

public interface AppariementRepository extends JpaRepository<Appariement, Integer> {

	@Query("select a from Appariement a where idAppariement = :idAppariement")
	List<Appariement> getAppariementById(@Param("idAppariement") Integer idAppariement);
	
	@Query("select a from Appariement a where tournoi = :tournoi and user1 = :user1 and user2 = :user2")
	List<Appariement> findAppariementByUsers(@Param("tournoi") Tournoi tournoi, @Param("user1") Username user1, @Param("user2") Username user2);	

	@Query("select a from Appariement a where tournoi = :tournoi")
	List<Appariement> findAppariementByTournament(@Param("tournoi") Tournoi tournoi);
	
	@Query("select a from Appariement a where tournoi = :tournoi and noRonde = :noRonde")
	List<Appariement> findAppariementByTournamentAndRound(@Param("tournoi") Tournoi tournoi, @Param("noRonde") Integer noRonde);
	
	@Query("select a from Appariement a where tournoi = :idTournoi and (user1 = :user or user2 =:user) and noRonde = :noRonde")
	List<Appariement> findPlayerAffected(@Param("tournoi") Tournoi idTournoi, @Param("User") Username user, @Param("noRonde") Integer noRonde);

}
