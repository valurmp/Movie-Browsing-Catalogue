package com.team18.MBC.core;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }
    public List<Review> getAllReviews(){ return reviewRepository.findAll();}


    public Review getReviewsById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

}