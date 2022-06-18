package com.listing.listing.response;

import com.listing.listing.model.Listing;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllListingResponse {
    private Boolean result;
    private List<Listing> listings;
}
