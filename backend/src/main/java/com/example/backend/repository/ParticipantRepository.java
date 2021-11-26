package com.example.backend.repository;

import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.model.Participant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ParticipantRepository  extends JpaRepository<Participant,Long> {
//    @Query(value = "SELECT count(*) FROM Participant where participantGenre ='Homme'")
//    public Long countM();
//    //count number girls
//    @Query(value = "SELECT count(*) FROM Participant where participantGenre='Femme'")
//    public Long countF();
	@Query("SELECT count(*) FROM Participant p WHERE p.participantGenre =:participantGenre")
	int findByparticipantGenre(@Param("participantGenre") ParticipantGenre participantGenre);
}
