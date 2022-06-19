package com.publicapi.response.external_api;

import com.publicapi.model.Listings;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetAllListingsApiResponse {
    private Boolean result;
    private List<Listings> listings;
}
