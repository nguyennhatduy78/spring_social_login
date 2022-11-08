package com.fpt.sociallogindemo.models.responses;

import com.fpt.sociallogindemo.models.FacebookUser;
import com.fpt.sociallogindemo.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacebookUserResponse {
    private Status status;
    private FacebookUser user;
}
