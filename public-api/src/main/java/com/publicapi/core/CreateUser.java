package com.publicapi.core;

import com.publicapi.model.User;
import com.publicapi.requests.CreateUserRequest;
import com.publicapi.response.CreateUsersResponse;
import com.publicapi.response.external_api.CreateUserApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateUser {
    private CreateUserRequest request;
    private static final String USER_API = "http://localhost:8081/users";


    public CreateUsersResponse createUser() {
        validateRequest();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", this.request.getName());

        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(requestBody, headers);

        CreateUserApiResponse response;
        try {
            response = restTemplate.exchange(USER_API, HttpMethod.POST, formEntity, CreateUserApiResponse.class).getBody();
        } catch (Exception e) {
            System.out.println("\n==== Error while calling users api ====\n"+ e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please try later, resources unreachable now.");
        }

        User user = null;
        if(response != null)
            user = response.getUsers();

        return CreateUsersResponse.builder().user(user).build();
    }

    private void validateRequest() {
        if(this.request.getName() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
    }

    public CreateUser withRequest(CreateUserRequest request) {
        this.request = request;
        return this;
    }
}
