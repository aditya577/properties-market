package com.publicapi.response.external_api;

import com.publicapi.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetAllUsersApiResponse {
    private Boolean result;
    private List<User> users;
}
