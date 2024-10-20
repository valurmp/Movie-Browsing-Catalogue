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

    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    public double getAverageRatingForMovie(long movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

}