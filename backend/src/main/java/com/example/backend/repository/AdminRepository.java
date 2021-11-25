package com.example.backend.repository;

import com.example.backend.model.Administrateur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Administrateur, Long> {
	List<Administrateur>findAllByRoleId(Long id);
}
