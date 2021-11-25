package com.example.backend.service;

import com.example.backend.model.Activite;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
public interface ActiviteService {
     String ajouterActivite (Activite activite);
     void modifierActivite (Long Id_activite, Activite activite);
     String supprimerActiviteById (Long Id_activite);
     Activite listeById (Long Id_activite);
     List<Activite> getAllActivite();
}
