package com.example.backend.service;

import com.example.backend.model.Activite;
import com.example.backend.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActiviteImp implements ActiviteService{
    @Autowired
    ActiviteRepository activiteRepository;

    @Override
    public String ajouterActivite(Activite activite) {
        this.activiteRepository.save(activite);
        return  "Ajout Ok"+activite.getType();
    }

    @Override
    @Transactional
    public void modifierActivite(Long Id, Activite activite) {
        Activite activiteAncien = activiteRepository.findById(Id).get();
        activiteAncien.setLibelle(activite.getLibelle());
        activiteAncien.setType(activite.getType());
        activiteAncien.setDate_debut(activite.getDate_debut());
        activiteAncien.setDate_fin(activite.getDate_fin());
        activiteAncien.setEtat(activite.getEtat());
    }

    @Override
    public String supprimerActiviteById(Long Id_activite) {
        this.activiteRepository.deleteById(Id_activite);
        return "Suppression de l'activité" ;
    }

    @Override
    public Activite listeById(Long Id_activite) {
        return activiteRepository.findById(Id_activite).get();
    }

    @Override
    public List<Activite> getAllActivite() {
        return activiteRepository.findAll();
    }


}
