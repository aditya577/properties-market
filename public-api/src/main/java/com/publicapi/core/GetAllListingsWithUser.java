package com.publicapi.core;

import com.publicapi.model.Listings;
import com.publicapi.model.User;
import com.publicapi.response.GetAllListingsWithUserResponse;
import com.publicapi.response.ListingWithUserResponse;
import com.publicapi.response.external_api.GetAllListingsApiResponse;
import com.publicapi.response.external_api.GetAllUsersApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class GetAllListingsWithUser {
    private static final String LISTINGS_API = "http://localhost:8082/listings";
    private static final String USER_API = "http://localhost:8081/users";
    private Pageable pageable;

    public GetAllListingsWithUserResponse getAll() {
        RestTemplate restTemplate = new RestTemplate();
        List<Listings> listingList;
        List<User> userList;
        try {
            listingList = Objects.requireNonNull(restTemplate.getForObject(LISTINGS_API, GetAllListingsApiResponse.class)).getListings();
            userList = Objects.requireNonNull(restTemplate.getForObject(USER_API, GetAllUsersApiResponse.class)).getUsers();
        } catch (Exception e) {
            System.out.println("\n==== Error while calling listing/users api ====\n"+ e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please try later, resources unreachable now.");
        }
        Map<Integer, User> userIdToUser = new HashMap<>();
        for (User user: userList)
            userIdToUser.put(user.getId(), user);

        List<ListingWithUserResponse> listings = new ArrayList<>();
        for (Listings listing: listingList) {
            ListingWithUserResponse response = ListingWithUserResponse.builder()
                    .id(listing.getId())
                    .listingType(listing.getListingType())
                    .price(listing.getPrice())
                    .createdAt(listing.getCreatedAt())
                    .updatedAt(listing.getUpdatedAt())
                    .user(userIdToUser.get(listing.getUserId()))
                    .build();
            listings.add(response);
        }

        Boolean result = listings.size() != 0;
        return GetAllListingsWithUserResponse.builder()
                .result(result)
                .listings(listings)
                .build();
    }

    public GetAllListingsWithUser withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }
}
