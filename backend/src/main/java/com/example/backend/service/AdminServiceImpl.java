package com.example.backend.service;

import com.example.backend.Exception.EntityNotFoundException;
import com.example.backend.Exception.ErrorCodes;
import com.example.backend.Exception.InvalidEntityException;
import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
import com.example.backend.validator.AdministrateurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;

	@Override
	public List<Administrateur> list() {
		return adminRepository.findAll();
	}

	@Override
	public Administrateur saveAdmin(Administrateur admin) {
		List<String> errors= AdministrateurValidator.validator(admin);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' admin n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
		}
		return adminRepository.save(admin);
	}

	@Override
	public Administrateur updateAdmin(Long id, Administrateur admin) {
		List<String> errors= AdministrateurValidator.validator(admin);
		if (!errors.isEmpty()){
			throw new InvalidEntityException("l' admin à modifier n'est pas valide", ErrorCodes.ADMINISTRATEUR_INVALID, errors);
		}
		Administrateur administrateur=adminRepository.getById(id);
		administrateur.setNom(admin.getNom());
		administrateur.setPrenom(admin.getPrenom());
		administrateur.setEmail(admin.getEmail());
		administrateur.setEtat(admin.getEtat());
		administrateur.setRole(admin.getRole());
		administrateur.setLogin(admin.getLogin());
		administrateur.setPassword(admin.getPassword());
		administrateur.setTelephone(admin.getTelephone());
		return adminRepository.save(administrateur);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
		
	}

	@Override
	public Administrateur AdminById(Long id) {
		return adminRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(
				"Aucun role avec l'id = " + id + " n'a ete trouvé dans la BDD", ErrorCodes.ADMININSTRATEUR_NOT_FOUND)
		);
	}

   
}
