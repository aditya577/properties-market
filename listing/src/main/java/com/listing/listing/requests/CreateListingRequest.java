package com.listing.listing.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateListingRequest {
    private Integer userId;
    private String listingType;
    private Integer price;
}
