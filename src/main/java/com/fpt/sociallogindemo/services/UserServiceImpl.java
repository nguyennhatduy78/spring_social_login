package com.fpt.sociallogindemo.services;

import com.fpt.sociallogindemo.constants.ResponseConstant;
import com.fpt.sociallogindemo.entities.User;
import com.fpt.sociallogindemo.models.Status;
import com.fpt.sociallogindemo.models.responses.UserResponse;
import com.fpt.sociallogindemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    @Override
    @Transactional
    public UserResponse validateUser(User user) {
        try{
            if(repository.isUserExisted(user.getEmail())){
                log.info("User found for {}. Update user now.", user);
                repository.updateUser(user);
            }else {
                log.info("No user found for {}. Insert user now", user);
                repository.insertUser(user);
            }
            return UserResponse.builder()
                    .user(repository.selectUserByEmail(user.getEmail()))
                    .status(Status.builder()
                            .code(ResponseConstant.SUCCESS).build())
                    .build();
        }catch (Exception e){
            log.error("Fail to validate user: {}",e.getMessage());
            return UserResponse.builder()
                    .status(Status.builder()
                            .code(ResponseConstant.SYSTEM_EXCEPTION).build())
                    .build();
        }
    }

    @Override
    public UserResponse selectUser(User user) {
        return null;
    }
}
