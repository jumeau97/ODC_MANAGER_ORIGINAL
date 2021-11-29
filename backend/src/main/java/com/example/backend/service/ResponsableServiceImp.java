package com.example.backend.service;

import java.util.List;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.validator.ParticipantValidator;
import com.example.backend.validator.ResponsableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.Responsable;
import com.example.backend.repository.ResponsableRepository;

@Service
public class ResponsableServiceImp implements ResponsableService {

	@Autowired
	ResponsableRepository responsableRepository;
	
	@Override
	public Responsable ajouter_responsable(Responsable responsable) {
		List<String> errors= ResponsableValidator.validator(responsable);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("le responsable n'est pas valide", ErrorCodes.PARTICIPATION_INVALID, errors);
		}

		return responsableRepository.save(responsable);
	}

	@Override
	public Responsable modifier_responsable(Long id, Responsable responsable) {
		List<String> errors= ResponsableValidator.validator(responsable);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("le responsable à modifier n'est pas valide", ErrorCodes.RESPONSABLE_INVALID, errors);
		}
		Responsable responsables = responsableRepository.findById(id).get();
		responsables.setNom(responsable.getNom());
		responsables.setPrenom(responsable.getPrenom());
		responsables.setTelephone(responsable.getTelephone());
		responsables.setDomaine(responsable.getDomaine());
		responsables.setType(responsable.getType());
		responsables.setEtat(responsable.getEtat());
		return responsableRepository.save(responsables);
	}

	@Override 
	public List<Responsable> list_responsable() {
		return responsableRepository.findAll();
	}

	@Override
	public Responsable afficher_responsable_by_id(Long id) {
		return responsableRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucun responsable avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.RESPONSABLE_NOT_FROUND)
		);
	}

	@Override
	public void supprimer_responsable(Long id) {
		responsableRepository.deleteById(id);
		
	}

}
