package com.publicapi.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateListingsRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private String listingType;
    @NotNull
    private Integer price;
}
