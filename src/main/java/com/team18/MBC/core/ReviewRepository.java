package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  //  List<Review> findByReviewId(Long id);
    List<Review> findAll();
    Optional<Review> findById(Long id);


}
