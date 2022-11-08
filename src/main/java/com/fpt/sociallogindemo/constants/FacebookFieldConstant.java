package com.fpt.sociallogindemo.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "facebook")
@Data
public class FacebookFieldConstant {
    private List<String> fields;
}
