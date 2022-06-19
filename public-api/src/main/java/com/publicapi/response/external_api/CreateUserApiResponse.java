package com.publicapi.response.external_api;

import com.publicapi.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserApiResponse {
    private Boolean result;
    private User users;
}
