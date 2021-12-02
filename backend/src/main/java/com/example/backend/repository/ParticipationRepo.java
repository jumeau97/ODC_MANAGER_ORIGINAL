package com.example.backend.repository;

import com.example.backend.enumeration.Etat;
import com.example.backend.model.LogActivites;
import com.example.backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepo extends JpaRepository<Participation, Long> {
    @Query(value = "SELECT p FROM Participation p WHERE p.activite.IdActivite = :IdActivite")
    List<Participation> getParticipationByActiviteAndEtat(@Param("IdActivite") Long IdActivite);
}
