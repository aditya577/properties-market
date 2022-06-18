package com.listing.listing.repository;

import com.listing.listing.model.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
    Page<Listing> findAll(Pageable pageable);
    List<Listing> findAllByUserId(Integer userId, Pageable pageable);
}
