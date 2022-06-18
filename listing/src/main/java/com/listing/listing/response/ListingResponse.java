package com.listing.listing.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListingResponse {
    private Integer userId;
    private String listingType;
    private Integer price;
    private Long createdAt;
    private Long updatedAt;
}
