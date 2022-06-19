package com.listing.listing.core;

import com.listing.listing.model.Listing;
import com.listing.listing.repository.ListingRepository;
import com.listing.listing.response.GetAllListingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllListings {

    private Integer userId;
    private Pageable pageable;
    @Autowired
    private ListingRepository repo;

    public GetAllListingResponse getAll() {
        List<Listing> listingList;
        if(this.userId == null)
            listingList = repo.findAll(this.pageable).getContent();
        else
            listingList = repo.findAllByUserId(userId, this.pageable);

        boolean result = listingList.size() != 0;

        return GetAllListingResponse.builder()
                .result(result)
                .listings(listingList)
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
