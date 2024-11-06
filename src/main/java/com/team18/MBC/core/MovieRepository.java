package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByType(String type);
    Optional<Movie> findByIdAndType(Long id, String type);

    @Query("SELECT DISTINCT m.genre FROM Movie m WHERE m.type = :type")
    List<String> findDistinctGenresByType(String type);
}
