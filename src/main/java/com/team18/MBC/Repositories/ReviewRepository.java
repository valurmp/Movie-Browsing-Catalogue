package com.team18.MBC.Repositories;

import com.team18.MBC.core.Movie;
import com.team18.MBC.core.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();
    Optional<Review> findById(Long id);
    List<Review> findByMovie(Movie movie);
    @Query("SELECT r FROM Review r WHERE r.movie.id = :movieId")
    List<Review> findByMovieId(@Param("movieId") Long movieId);

    void deleteById(Long reviewId);
}
