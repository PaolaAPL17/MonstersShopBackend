package com.femcoders.monsters_shop.dtos.review;

import com.femcoders.monsters_shop.models.Product;
import com.femcoders.monsters_shop.models.Review;

public class ReviewMapper {
    public static Review dtoToEntity (Product product, ReviewRequest dto) {
        return new Review(dto.username(), dto.rating(), dto.body(), product);
    }

    public static ReviewResponse entityToDto (Review review){
        Long productId = review.getProduct().getId();
        return new ReviewResponse(productId, review.getId(), review.getUsername(), review.getRating(), review.getBody());
    }
}
