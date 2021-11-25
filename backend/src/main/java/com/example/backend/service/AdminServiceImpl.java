package com.example.backend.service;

import com.example.backend.model.Administrateur;
import com.example.backend.repository.AdminRepository;
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
		return adminRepository.save(admin);
	}

	@Override
	public Administrateur updateAdmin(Long id, Administrateur admin) {
		return adminRepository.save(admin);
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
		
	}

	@Override
	public Administrateur AdminById(Long id) {
		return adminRepository.findById(id).get();
	}

   
}
