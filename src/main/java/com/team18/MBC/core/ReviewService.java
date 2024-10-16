package com.team18.MBC.core;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }
    public List<Review> getAllReviews(){ return reviewRepository.findAll();}




}