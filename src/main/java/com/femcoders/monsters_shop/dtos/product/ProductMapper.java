package com.femcoders.monsters_shop.dtos.product;

import com.femcoders.monsters_shop.models.Product;

public class ProductMapper {
    public static Product dtoEntity (ProductRequest dto){
        return new Product(dto.name(), dto.price(), dto.imageUrl(), dto.rating(), dto.reviewCount(), dto.featured());
    }

    public static ProductResponse entityToDto (Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.getRating(), product.getReviewCount(), product.getFeatured());
    }
}
