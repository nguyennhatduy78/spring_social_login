package com.fpt.sociallogindemo.services.socials;

import com.fpt.sociallogindemo.constants.FacebookFieldConstant;
import com.fpt.sociallogindemo.constants.ResponseConstant;
import com.fpt.sociallogindemo.models.FacebookUser;
import com.fpt.sociallogindemo.models.Status;
import com.fpt.sociallogindemo.models.responses.FacebookUserResponse;
import com.fpt.sociallogindemo.utils.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacebookSocialService {

    private final RestTemplate restTemplate;

    private final FacebookFieldConstant fields;

    @Value("${facebook.profile}")
    private String profileUrl;

    public FacebookUserResponse selectUserProfile(String accessToken){
        try{
            URI uri = new URI(profileUrl);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri)
                    .queryParam("fields",fieldToRequest())
                    .queryParam("access_token", accessToken);
            ResponseEntity<FacebookUser> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, FacebookUser.class);
            if(!responseEntity.getStatusCode().is2xxSuccessful() || Validator.isEmpty(responseEntity.getBody())){
                return FacebookUserResponse.builder()
                        .status(Status.builder()
                                .code(ResponseConstant.AUTHENTICATION_EXCEPTION)
                                .message("Fail to select user profile")
                                .build())
                        .build();
            }
            return FacebookUserResponse.builder()
                    .status(Status.builder()
                            .code(ResponseConstant.SUCCESS)
                            .message("Successfully select user profile")
                            .build())
                    .user(responseEntity.getBody())
                    .build();
        } catch(Exception e){
            return FacebookUserResponse.builder()
                    .status(Status.builder()
                            .code(ResponseConstant.SYSTEM_EXCEPTION)
                            .message("System exception: "+e.getMessage())
                            .build())
                    .build();
        }
    }

    private String fieldToRequest(){
        return String.join(",",fields.getFields());
    }
}
