package com.example.backend.service;


import com.example.backend.enumeration.ParticipantGenre;
import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.model.Participant;
import com.example.backend.repository.ParticipantRepository;
import com.example.backend.validator.AdministrateurValidator;
import com.example.backend.validator.ParticipantValidator;
import com.example.backend.validator.ParticipationVaidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImplement implements ParticipantService {
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public Participant addParticipant(Participant participant)
	{
		List<String> errors= ParticipantValidator.validator(participant);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("le participant n'est pas valide", ErrorCodes.PARTICIPATION_INVALID, errors);
		}
		return participantRepository.save(participant);
	}

	@Override
	// mettre a jour de participant
	public Participant updateParticipant(Long id, Participant participant) {
		List<String> errors= ParticipantValidator.validator(participant);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("le participant à modifier n'est pas valide", ErrorCodes.PARTICIPANT_INVALID, errors);
		}
		// return participantRepository.save(participant);
		Participant mod = participantRepository.getById(id);
		mod.setNom_complet(participant.getNom_complet());
		mod.setEmail(participant.getEmail());
		mod.setDomaine(participant.getDomaine());
		mod.setStructure(participant.getStructure());
		mod.setTelephone(participant.getTelephone());
		return participantRepository.save(mod);

	}

	@Override
	public List<Participant> listParticipant() {
		return participantRepository.findAll();
	}

	@Override
	public void deleteParticipant(Long id) {
		participantRepository.deleteById(id);
	}

	@Override
	public Participant ParticipantById(Long id)
	{
		return participantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucun participant avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.PARTICIPANT_NOT_FOUND)
		);
	}

	@Override
	public int findByparticipantGenre(ParticipantGenre genre) {
		
		return participantRepository.findByparticipantGenre(genre);
	}

//	@Override
//	public Long countM() {
//		return participantRepository.countM();
//	}
//
//	@Override
//	public Long countF() {
//		return participantRepository.countF() ;
//	}

}
