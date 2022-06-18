package com.listing.listing.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateListingResponse {
    private Boolean result;
    private ListingResponse listing;
}
