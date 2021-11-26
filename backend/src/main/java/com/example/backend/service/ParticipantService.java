package com.example.backend.service;

import com.example.backend.model.Participant;
import java.util.List;
public interface ParticipantService {
    public Participant addParticipant(Participant participant);
    public Participant updateParticipant(Long id, Participant participant);
    public List<Participant> listParticipant();
    void deleteParticipant( Long id);
    Participant ParticipantById(Long id);
    //count number people
    public Long countM();
    public Long countF();
}
