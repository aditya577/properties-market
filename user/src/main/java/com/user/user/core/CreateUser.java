package com.user.user.core;

import com.user.user.model.Users;
import com.user.user.repository.UserRepository;
import com.user.user.response.GetUserByIdResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CreateUser {
    private String name;
    private final UserRepository repo;

    public CreateUser(UserRepository repo) {
        this.repo = repo;
    }

    public GetUserByIdResponse createUser() {
        Users users = new Users(this.name,
                new Timestamp(System.currentTimeMillis()).getTime(),
                new Timestamp(System.currentTimeMillis()).getTime());
        Users createdUsers = repo.save(users);
        boolean result = createdUsers != null;
        return GetUserByIdResponse.builder()
                .result(result)
                .users(createdUsers)
                .build();
    }

    public CreateUser withUserName(String name) {
        this.name = name;
        return this;
    }
}
