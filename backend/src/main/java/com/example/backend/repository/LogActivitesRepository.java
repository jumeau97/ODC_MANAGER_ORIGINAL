package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.backend.model.LogActivites;
@RepositoryRestResource
public interface LogActivitesRepository extends JpaRepositoryImplementation<LogActivites, Long> {

}
