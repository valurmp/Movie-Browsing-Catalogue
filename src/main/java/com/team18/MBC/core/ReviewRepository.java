package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();
    Optional<Review> findById(Long id);
    List<Review> findByMovieId(Long movieId);

}
