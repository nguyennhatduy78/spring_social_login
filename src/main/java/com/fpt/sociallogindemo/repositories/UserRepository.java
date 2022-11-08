package com.fpt.sociallogindemo.repositories;

import com.fpt.sociallogindemo.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    User selectUserByEmail(String email);
    Boolean isUserExisted(String email);
    void updateUser(User user);

    void insertUser(User user);
}
