package com.publicapi.response;

import com.publicapi.model.Listings;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateListingsResponse {
    private Listings listing;
}
