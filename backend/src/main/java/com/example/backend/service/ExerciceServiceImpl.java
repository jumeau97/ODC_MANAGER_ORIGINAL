package com.example.backend.service;

import java.util.List;


import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.validator.AdministrateurValidator;
import com.example.backend.validator.ExerciceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.model.Exercice;
import com.example.backend.repository.ExerciceRepository;

@Service
public class ExerciceServiceImpl implements ExerciceService{
	@Autowired
	ExerciceRepository exerciceRepository;

	@Override
	public void ajoutExercice(Exercice exercice)
	{
		List<String> errors= ExerciceValidator.validator(exercice);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' admin n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
		}
		 exerciceRepository.save(exercice);		
	}

	@Override
	public List<Exercice> listExercice() {
		
		return exerciceRepository.findAll();
	}

	@Override
	public Exercice ExerciceById(Long id) {

		return exerciceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucun role avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.ADMININSTRATEUR_NOT_FOUND)
		);
	}

	@Override
	@Transactional
	public void updateExcercice(Long id, Exercice exercice) {
		List<String> errors= ExerciceValidator.validator(exercice);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l'exercie n'est pas valide", ErrorCodes.EXERCICE_INVALID, errors);
		}
		Exercice exercice1 = exerciceRepository.findById(id).get();
        exercice1.setAnnee(exercice.getAnnee());
        exercice1.setDate_debut(exercice.getDate_debut());
        exercice1.setDate_fin(exercice.getDate_fin());
        exercice1.setStatut(exercice.getStatut());
        exercice1.setEtat(exercice.getEtat());
	}

	@Override
	public void deleteExercice(Long id) {
		exerciceRepository.deleteById(id);
		
	}

	@Override
	public List<Exercice> getExerciceByAnnee(String annee) {

		return exerciceRepository.getExerciceByAnnee(annee);
	}

	

}
