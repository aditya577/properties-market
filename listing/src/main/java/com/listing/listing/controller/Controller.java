package com.listing.listing.controller;

import com.listing.listing.core.CreateListing;
import com.listing.listing.core.GetAllListings;
import com.listing.listing.requests.CreateListingRequest;
import com.listing.listing.response.CreateListingResponse;
import com.listing.listing.response.GetAllListingResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listings")
public class Controller {
    private final CreateListing createListing;
    private final GetAllListings getAllListings;

    public Controller(CreateListing createListing, GetAllListings getAllListings) {
        this.createListing = createListing;
        this.getAllListings = getAllListings;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CreateListingResponse createListing(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "price") Integer price,
            @RequestParam(name = "listingType") String listingType) {
        CreateListingRequest request = CreateListingRequest.builder()
                .listingType(listingType)
                .price(price)
                .userId(userId)
                .build();
        return createListing.withRequest(request).create();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetAllListingResponse getAllListing(@RequestParam(name="userId", required = false) Integer userId,
                                               @PageableDefault(page = 0, value = 10) Pageable pageable) {
        return getAllListings.withUserId(userId).withPageable(pageable).getAll();
    }

}
