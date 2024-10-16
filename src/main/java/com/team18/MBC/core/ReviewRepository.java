package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  //  List<Review> findByReviewId(Long id);
    List<Review> findAll();

}
