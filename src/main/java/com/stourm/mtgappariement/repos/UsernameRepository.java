package com.stourm.mtgappariement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stourm.mtgappariement.entities.Username;

public interface UsernameRepository extends JpaRepository<Username, Integer>{

	@Query("select un from Username un where idUser = :idUser")
	List<Username> getUserNameById(@Param("idUser") Integer idUser);
	
	@Query("select un from Username un  join fetch un.tournois t where t.nomTournoi = :nomTournoi ")
	List<Username> getAllUsersByTournament(@Param("nomTournoi") String nomTournoi);
	
	@Query("select un from Username un where nom = :nom and prenom = :prenom and pseudo = :pseudo")
	List<Username> getUserNameByNameAndPseudo(@Param("nom") String nom, @Param("prenom") String prenom, @Param("pseudo") String pseudo);
}
