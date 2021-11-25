package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.backend.model.Exercice;
@RepositoryRestResource
public interface ExerciceRepository extends JpaRepository<Exercice, Long>{
	public List<Exercice> getExerciceByAnnee(String annee);

}
