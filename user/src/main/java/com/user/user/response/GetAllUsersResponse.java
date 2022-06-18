package com.user.user.response;

import com.user.user.model.Users;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllUsersResponse {
    private Boolean result;
    private List<Users> users;
}
