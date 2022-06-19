package com.publicapi.response;

import com.publicapi.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUsersResponse {
    private User user;
}
