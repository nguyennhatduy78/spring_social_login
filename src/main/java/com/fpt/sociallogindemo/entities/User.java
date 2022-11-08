package com.fpt.sociallogindemo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String uuid;
    private String name;
    private String password;
    private String email;
    private String loginType;
    private String createDatetime;
    private String updatedDatetime;
}
