package com.publicapi.response.external_api;

import com.publicapi.model.Listings;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateListingApiResponse {
    private Boolean result;
    private Listings listing;
}
