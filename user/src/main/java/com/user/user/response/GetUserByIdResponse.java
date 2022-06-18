package com.user.user.response;

import com.user.user.model.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserByIdResponse {
    private Boolean result;
    private Users users;
}
