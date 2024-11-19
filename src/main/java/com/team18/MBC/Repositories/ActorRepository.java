package com.team18.MBC.Repositories;

import com.team18.MBC.core.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findAll();
    Optional<Actor> findById(Long id);

}
