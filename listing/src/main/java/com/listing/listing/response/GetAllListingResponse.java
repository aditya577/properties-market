package com.listing.listing.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllListingResponse {
    private Boolean result;
    private List<ListingResponse> listings;
}
