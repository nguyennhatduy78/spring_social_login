package com.fpt.sociallogindemo.controllers;

import com.fpt.sociallogindemo.constants.ApiPathConstant;
import com.fpt.sociallogindemo.constants.ResponseConstant;
import com.fpt.sociallogindemo.models.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ApiPathConstant.ROOT)
public class UserController {

    @GetMapping
    public ResponseEntity<Status> healthCheck(){
        return ResponseEntity.ok(Status.builder()
                        .code(ResponseConstant.SUCCESS)
                        .message("Health check !")
                .build());
    }
}
