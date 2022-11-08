package com.fpt.sociallogindemo.models.requests;

import com.fpt.sociallogindemo.constants.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private CommonConstant.LOGIN_TYPE loginType;
    private String token;
}
