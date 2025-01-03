package com.team18.MBC.Services;

import com.team18.MBC.Repositories.ActorRepository;
import com.team18.MBC.core.Actor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ActorService {

    private ActorRepository actorRepository;
    public ActorService(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }
    public List<Actor> getAllActors(){ return actorRepository.findAll();}


    public Actor getActorsById(Long id) {
        Optional<Actor> actor = actorRepository.findById(id);
        return actor.orElse(null);
    }




}