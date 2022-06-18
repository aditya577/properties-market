package com.listing.listing.core;

import com.listing.listing.model.Listing;
import com.listing.listing.repository.ListingRepository;
import com.listing.listing.requests.CreateListingRequest;
import com.listing.listing.response.CreateListingResponse;
import com.listing.listing.response.ListingResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CreateListing {
    private CreateListingRequest request;
    private final ListingRepository repo;

    public CreateListing(ListingRepository repo) {
        this.repo = repo;
    }

    public CreateListingResponse create() {
        Listing listing = new Listing(request.getUserId(), request.getListingType(), request.getPrice(),
                new Timestamp(System.currentTimeMillis()).getTime(), new Timestamp(System.currentTimeMillis()).getTime());

        Listing createdListing = repo.save(listing);
        CreateListingResponse response;
        if(createdListing != null)
             response = CreateListingResponse.builder()
                     .result(true)
                     .listing(ListingResponse.builder()
                             .listingType(createdListing.getListingType())
                             .userId(createdListing.getUserId())
                             .price(createdListing.getPrice())
                             .createdAt(createdListing.getCreatedAt())
                             .updatedAt(createdListing.getUpdatedAt())
                             .build())
                     .build();
        else
            response = CreateListingResponse.builder()
                    .result(false)
                    .listing(null)
                    .build();


        return response;
    }

    public CreateListing withRequest(CreateListingRequest request) {
        this.request = request;
        return this;
    }
}
