package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.model.Participation;
import com.example.backend.repository.ParticipationRepo;
import com.example.backend.validator.AdministrateurValidator;
import com.example.backend.validator.ParticipationVaidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartcipationServiceImplement implements ParticipationService {

	@Autowired
	ParticipationRepo participationRepo;

	@Override
	public Participation ajouterParticipation(Participation p) {
		List<String> errors= ParticipationVaidator.validator(p);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("la presence n'est pas valide", ErrorCodes.PARTICIPATION_INVALID, errors);
		}
		return participationRepo.save(p);
	}

	@Override
	public void deleteParticipation(Long id) {
		participationRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void updateParticipation(Long id, Participation p) {
		List<String> errors= ParticipationVaidator.validator(p);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("la presence à modifier est invalide", ErrorCodes.PARTICIPATION_INVALID, errors);
		}
		Participation APP = participationRepo.findById(id).get();
		System.out.println(id);
		APP.setHeure_arriver(p.getHeure_arriver());
		APP.setActivite(p.getActivite());
		APP.setParticipant(p.getParticipant());
		APP.setAdministrateur(p.getAdministrateur());
		APP.setPresence(p.isPresence());

	}

	@Override
	public List<Participation> getAllParticipation() {
		return participationRepo.findAll();
	}

	@Override
	public Participation getParticipationById(Long id) {

		return participationRepo.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucune presence avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.PARTICIPATION_NOT_FOUND)
		);
	}
}
