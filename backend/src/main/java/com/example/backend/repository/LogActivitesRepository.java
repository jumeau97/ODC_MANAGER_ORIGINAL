package com.example.backend.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.backend.model.LogActivites;

import java.util.List;

@RepositoryRestResource
public interface LogActivitesRepository extends JpaRepositoryImplementation<LogActivites, Long> {
    @Query(value = "SELECT lo FROM LogActivites lo WHERE lo.activite.IdActivite = :IdActivite")
    List<LogActivites> findAllByActiviteId(@Param("IdActivite") Long IdActivite);
    //List<LogActivites> findAllByActiviteId(Long IdActivite);
}
