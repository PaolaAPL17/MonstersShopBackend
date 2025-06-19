package com.femcoders.monsters_shop.dtos.review;

public record ReviewResponse (
        Long productId,
        Long Id,
        String username,
        double rating,
        String body
) {
}
