package com.example.backend.repository;

import com.example.backend.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
