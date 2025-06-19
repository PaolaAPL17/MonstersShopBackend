package com.femcoders.monsters_shop.controllers;

import com.femcoders.monsters_shop.dtos.review.ReviewRequest;
import com.femcoders.monsters_shop.dtos.review.ReviewResponse;
import com.femcoders.monsters_shop.services.ReviewService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable Long id){
        List<ReviewResponse> reviews = reviewService.getReviews(id);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponse> addReview (@Valid @RequestBody ReviewRequest reviewRequest) {
        return new ResponseEntity<>(reviewService.addReview(reviewRequest), HttpStatus.CREATED);
    }
}
