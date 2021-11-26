package com.example.backend.repository;

import com.example.backend.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ParticipantRepository  extends JpaRepository<Participant,Long> {
    @Query("SELECT p FROM Participant p WHERE p.email = :email")
    Optional<Participant> findParticipant(@Param("email") String email);

}
