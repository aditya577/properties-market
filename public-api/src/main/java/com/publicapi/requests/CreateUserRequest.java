package com.publicapi.requests;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotNull
    private String name;
}
