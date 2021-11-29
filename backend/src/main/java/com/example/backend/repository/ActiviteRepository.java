package com.example.backend.repository;

import com.example.backend.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    @Query(value = "SELECT act FROM Activite act WHERE act.exercice.annee =:annee")
    List<Activite> findActiviteByAnnee(@Param(value = "annee") String annee);
    List<Activite> getActiviteByDateDebutGreaterThanEqualAndDateDebutLessThanEqual(LocalDate start, LocalDate end);
}
