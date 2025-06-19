package com.femcoders.monsters_shop.services;

import com.femcoders.monsters_shop.dtos.review.ReviewMapper;
import com.femcoders.monsters_shop.dtos.review.ReviewRequest;
import com.femcoders.monsters_shop.dtos.review.ReviewResponse;
import com.femcoders.monsters_shop.models.Product;
import com.femcoders.monsters_shop.models.Review;
import com.femcoders.monsters_shop.repository.ProductRepository;
import com.femcoders.monsters_shop.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ReviewService(ReviewRepository reviewRepository, ProductService productService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public List<ReviewResponse> getReviews(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
        return product.getReviews().stream().map(ReviewMapper::entityToDto).toList();
    }

    public ReviewResponse addReview(ReviewRequest reviewRequest){
        Product product = productRepository.findById(reviewRequest.productId()).orElseThrow(() -> new NoSuchElementException("Product not found"));
        Review newReview = ReviewMapper.dtoToEntity(product, reviewRequest);
        Review savedReview = reviewRepository.save(newReview);
        return ReviewMapper.entityToDto(savedReview);
    }
}
