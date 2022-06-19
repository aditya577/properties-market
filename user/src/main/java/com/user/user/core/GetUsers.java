package com.user.user.core;

import com.user.user.model.Users;
import com.user.user.repository.UserRepository;
import com.user.user.response.GetAllUsersResponse;
import com.user.user.response.GetUserByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetUsers {

    private Integer id;
    private Pageable pageable;
    @Autowired
    private UserRepository repo;

    public GetAllUsersResponse getAll() {
        List<Users> users = repo.findAll(this.pageable).getContent();
        boolean result = users.size() != 0;
        return GetAllUsersResponse.builder()
                .result(result)
                .users(users)
                .build();
    }

    public GetUserByIdResponse getUserById() {
        Users user = null;
        Optional<Users> optionalUsers =  repo.findById(this.id);

        if (optionalUsers.isPresent())
            user = optionalUsers.get();

        boolean result = user != null;
        return GetUserByIdResponse.builder()
                .result(result)
                .users(user)
                .build();
    }

    public GetUsers withUserId(Integer id) {
        this.id = id;
        return this;
    }

    public GetUsers withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }
}
