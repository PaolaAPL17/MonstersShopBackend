package com.femcoders.monsters_shop.models;

import  jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private double rating;
    private String body;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "product_id", nullable = false)
    private Product product;

    public  Review(){
    }

    public Review(Product product, Long id, String username, double rating, String body){
        this.product = product;
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.body = body;
    }

    public Review(String username, double rating, String body, Product product) {
        this.username = username;
        this.rating = rating;
        this.body = body;
        this.product = product;
    }

    public Long getId(){
        return  id;
    }

    public String getUsername(){
        return username;
    }

    public double getRating(){
        return rating;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public String getBody(){
        return body;
    }

    public  Product getProduct(){
        return  product;
    }

    public void setProduct(Product product){
        this.product = product;
    }
}
