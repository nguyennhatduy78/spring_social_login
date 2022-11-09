package com.fpt.sociallogindemo.controllers;

import com.fpt.sociallogindemo.constants.ApiPathConstant;
import com.fpt.sociallogindemo.constants.CommonConstant;
import com.fpt.sociallogindemo.constants.ResponseConstant;
import com.fpt.sociallogindemo.entities.User;
import com.fpt.sociallogindemo.models.FacebookUser;
import com.fpt.sociallogindemo.models.Status;
import com.fpt.sociallogindemo.models.requests.LoginRequest;
import com.fpt.sociallogindemo.models.responses.FacebookUserResponse;
import com.fpt.sociallogindemo.models.responses.UserResponse;
import com.fpt.sociallogindemo.services.UserService;
import com.fpt.sociallogindemo.services.socials.FacebookSocialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(ApiPathConstant.ROOT)
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final FacebookSocialService fbService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Status> healthCheck(){
        return ResponseEntity.ok(Status.builder()
                        .code(ResponseConstant.SUCCESS)
                        .message("Health check !")
                .build());
    }

    @PostMapping(value = ApiPathConstant.LOG_IN)
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){
        log.info("Login with request: {}", request);
        UserResponse response = new UserResponse();

        switch (request.getLoginType()){
            case FB:
                log.info("Login type: {}",request.getLoginType().name());
                FacebookUserResponse facebookUserResponse = fbService.selectUserProfile(request.getToken());
                if(facebookUserResponse.getStatus().getCode() != 0){
                    response.setStatus(facebookUserResponse.getStatus());
                } else {
                    FacebookUser fbUser = facebookUserResponse.getUser();
                    User user = User.builder()
                            .loginType(CommonConstant.LOGIN_TYPE.FB.name())
                            .name(fbUser.getName())
                            .email(fbUser.getEmail())
                            .password(fbUser.getId())
                            .uuid(UUID.randomUUID().toString())
                            .build();
                    log.info("User info validating....");
                    UserResponse userResponse = userService.validateUser(user);
                    if(userResponse.getStatus().getCode() != 0){
                        log.error("Fail to validate user");
                        response.setStatus(userResponse.getStatus());
                    }else {
                        response.setUser(userResponse.getUser());
                        response.setStatus(Status.builder()
                                .code(ResponseConstant.SUCCESS)
                                .message("User found!")
                                .build());
                    }
                }
                break;
            case GG: break;
            default: break;
        }
        return ResponseEntity.ok(response);
    }
}
