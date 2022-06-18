package com.user.user.controller;

import com.user.user.core.CreateUser;
import com.user.user.core.GetUsers;
import com.user.user.response.GetAllUsersResponse;
import com.user.user.response.GetUserByIdResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUser createUser;
    private final GetUsers getUsers;

    public UserController(CreateUser createUser, GetUsers getUsers) {
        this.createUser = createUser;
        this.getUsers = getUsers;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetUserByIdResponse createUser(@RequestParam(name="name") String name) {
        return createUser.withUserName(name).createUser();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetAllUsersResponse getAllUsers(@PageableDefault(page = 0, value = 10) Pageable pageable) {
        return getUsers.withPageable(pageable).getAll();
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public GetUserByIdResponse getUserById(@PathVariable(name = "userId") Integer id) {
        return getUsers.withUserId(id).getUserById();
    }
}
