package com.listing.listing.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String listingType;
    private Integer price;
    private Long createdAt;
    private Long updatedAt;

    public Listing(Integer userId, String listingType, Integer price, Long createdAt, Long updatedAt) {
        this.userId = userId;
        this.listingType = listingType;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
