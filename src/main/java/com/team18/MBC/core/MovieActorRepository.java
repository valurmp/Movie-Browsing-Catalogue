package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {
    List<MovieActor> findByMovieId(Long movieId);
}