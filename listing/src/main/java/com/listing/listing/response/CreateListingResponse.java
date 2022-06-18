package com.listing.listing.response;

import com.listing.listing.model.Listing;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateListingResponse {
    private Boolean result;
    private Listing listing;
}
