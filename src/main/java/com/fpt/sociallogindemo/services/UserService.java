package com.fpt.sociallogindemo.services;

import com.fpt.sociallogindemo.entities.User;
import com.fpt.sociallogindemo.models.responses.UserResponse;

public interface UserService {
    UserResponse validateUser(User user);
    UserResponse selectUser(User user);

}
