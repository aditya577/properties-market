package com.publicapi.controller;

import com.publicapi.core.CreateListings;
import com.publicapi.core.CreateUser;
import com.publicapi.core.GetAllListingsWithUser;
import com.publicapi.requests.CreateListingsRequest;
import com.publicapi.requests.CreateUserRequest;
import com.publicapi.response.CreateListingsResponse;
import com.publicapi.response.CreateUsersResponse;
import com.publicapi.response.GetAllListingsWithUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public-api")
public class PublicApiController {
    @Autowired
    private GetAllListingsWithUser getAllListingsWithUser;
    @Autowired
    private CreateUser createUser;
    @Autowired
    private CreateListings createListings;

//    public PublicApiController(GetAllListingsWithUser getAllListingsWithUser, CreateUser createUser, CreateListings createListings) {
//        this.getAllListingsWithUser = getAllListingsWithUser;
//        this.createUser = createUser;
//        this.createListings = createListings;
//    }

    @GetMapping(path = "/listings", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAllListingsWithUserResponse getAllListings(@PageableDefault(page = 0, value = 10) Pageable pageable) {
        return getAllListingsWithUser.withPageable(pageable).getAll();
    }

    @PostMapping(path = "/listings", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateListingsResponse createListings(@RequestBody CreateListingsRequest createListingsRequest) {
        return createListings.withRequest(createListingsRequest).createListing();
    }

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateUsersResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return createUser.withRequest(createUserRequest).createUser();
    }



}
