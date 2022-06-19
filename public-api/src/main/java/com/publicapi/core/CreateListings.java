package com.publicapi.core;

import com.publicapi.model.Listings;
import com.publicapi.model.User;
import com.publicapi.requests.CreateListingsRequest;
import com.publicapi.response.CreateListingsResponse;
import com.publicapi.response.external_api.CreateListingApiResponse;
import com.publicapi.response.external_api.CreateUserApiResponse;
import com.publicapi.response.external_api.GetAllUsersApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class CreateListings {
    private CreateListingsRequest request;
    private static final String LISTINGS_API = "http://localhost:8082/listings";
    private static final String USER_API = "http://localhost:8081/users";

    public CreateListingsResponse createListing() {
        validateRequest();
        RestTemplate restTemplate = new RestTemplate();
        String url = USER_API + "/" + this.request.getUserId();
        CreateUserApiResponse user = Objects.requireNonNull(restTemplate.getForObject(url, CreateUserApiResponse.class));
        if (user.getUsers() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "userId not found");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("userId", this.request.getUserId().toString());
        requestBody.add("listingType", this.request.getListingType());
        requestBody.add("price", this.request.getPrice().toString());

        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(requestBody, headers);

        CreateListingApiResponse response;
        try {
            response = restTemplate.exchange(LISTINGS_API, HttpMethod.POST, formEntity, CreateListingApiResponse.class).getBody();
        } catch (Exception e) {
            System.out.println("\n==== Error while calling users api ====\n"+ e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please try later, resources unreachable now.");
        }

        Listings listing = null;
        if(response != null)
            listing = response.getListing();

        return CreateListingsResponse.builder().listing(listing).build();
    }

    private void validateRequest() {
        if(this.request.getUserId() == null || this.request.getListingType() == null || this.request.getPrice() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request-params are invalid");
    }


    public CreateListings withRequest(CreateListingsRequest request) {
        this.request = request;
        return this;
    }
}
