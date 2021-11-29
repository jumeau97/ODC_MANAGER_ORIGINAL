package com.example.backend.validator;

import com.example.backend.model.Participation;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ParticipationVaidator {
    public static List<String> validator(Participation participation){
        List<String> error= new ArrayList<>();
        if(participation==null){
            error.add("Veuillez renseigner tous les champs ");
        }
        if(participation.getActivite()==null || participation.getActivite().getDateDebut()==null){
            error.add("Veuillez renseigner l'activite ");
        }
        if(participation.getParticipant()==null || participation.getParticipant().getNom_complet()==null){
            error.add("Veuillez renseigner le participant ");
        }

        if(participation.getAdministrateur()==null || participation.getAdministrateur().getLogin()==null){
            error.add("Veuillez renseigner la structure ");
        }

        return error;
    }
}
