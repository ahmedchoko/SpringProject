package tn.esprit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Categorieclient;
import tn.esprit.entities.Client;
import tn.esprit.entities.Profession;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long>{
	
	@Query("SELECT c FROM Client c WHERE c.profession= :profession")
	List<Client> retrieveClientsByProfession(@Param("profession") Profession profession);
	
	@Modifying
	@Query("Update Client c set c.categorieclient = :categorie where c.profession =:profession")
	long updateClientCategorieByProfession(@Param("categorie") Categorieclient categorieClient, @Param("profession") Profession profession);
	
	@Modifying
	@Query("DELETE FROM Client c WHERE c.categorieclient= :categorie AND c.profession =:profession")
	long deleteClientByCategorieClientAndProfession(@Param("categorie") Categorieclient categorieclient, @Param("profession") Profession profession);
	
	@Modifying
	@Query(value = "INSERT INTO Client (nom, prenom,dateNaissance,email,password,profession,categorieClient) VALUES (:nom,:prenom, :dateN, :email, :password, :profession, :categorieClient)",nativeQuery = true)
	long insertClient(@Param("nom") String nom, @Param("prenom") String prenom,@Param("dateN") 	String dateNaissance, @Param("email") String email, @Param("password") String password, @Param("profession") Profession profession, @Param("categorieClient") Categorieclient categorieClient);
	
}
