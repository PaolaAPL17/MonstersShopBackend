package com.femcoders.monsters_shop.services;

import com.femcoders.monsters_shop.dtos.product.ProductMapper;
import com.femcoders.monsters_shop.dtos.product.ProductRequest;
import com.femcoders.monsters_shop.dtos.product.ProductResponse;
import com.femcoders.monsters_shop.models.Product;
import com.femcoders.monsters_shop.repository.ProductRepository;
import com.femcoders.monsters_shop.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
       return products.stream().map(ProductMapper::entityToDto).toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return ProductMapper.entityToDto(product);
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product newProduct = ProductMapper.dtoEntity(productRequest);
        Product saveProduct = productRepository.save(newProduct);
        return ProductMapper.entityToDto(saveProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        existingProduct.setName(productRequest.name());
        existingProduct.setPrice(productRequest.price());
        existingProduct.setImageUrl(productRequest.imageUrl());
        existingProduct.setRating(productRequest.rating());
        existingProduct.setFeatured(productRequest.featured());
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.entityToDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}