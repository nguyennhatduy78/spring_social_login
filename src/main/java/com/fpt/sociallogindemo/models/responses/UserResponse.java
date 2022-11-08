package com.fpt.sociallogindemo.models.responses;

import com.fpt.sociallogindemo.entities.User;
import com.fpt.sociallogindemo.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Status status;
    private User user;
}
