package com.listing.listing.core;

import com.listing.listing.model.Listing;
import com.listing.listing.repository.ListingRepository;
import com.listing.listing.response.GetAllListingResponse;
import com.listing.listing.response.ListingResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllListings {

    private Integer userId;
    private Pageable pageable;
    private final ListingRepository repo;

    public GetAllListings(ListingRepository repo) {
        this.repo = repo;
    }

    public GetAllListingResponse getAll() {
        List<Listing> listingList;
        List<ListingResponse> listingResponseList = new ArrayList<>();
        if(this.userId == null)
            listingList = repo.findAll(this.pageable).getContent();
        else
            listingList = repo.findAllByUserId(userId, this.pageable);

        for (Listing listing : listingList) {
            ListingResponse listingResponse = ListingResponse.builder()
                    .listingType(listing.getListingType())
                    .userId(listing.getUserId())
                    .price(listing.getPrice())
                    .createdAt(listing.getCreatedAt())
                    .updatedAt(listing.getUpdatedAt())
                    .build();
            listingResponseList.add(listingResponse);
        }

        boolean result = listingResponseList.size() != 0;

        return GetAllListingResponse.builder()
                .result(result)
                .listings(listingResponseList)
                .build();

    }

    public GetAllListings withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public GetAllListings withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }
}
