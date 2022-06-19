package com.publicapi.response;

import com.publicapi.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListingWithUserResponse {
    private Integer id;
    private String listingType;
    private Integer price;
    private Long createdAt;
    private Long updatedAt;
    private User user;
}
