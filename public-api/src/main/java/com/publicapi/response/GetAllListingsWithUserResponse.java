package com.publicapi.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllListingsWithUserResponse {
    private Boolean result;
    private List<ListingWithUserResponse> listings;
}
