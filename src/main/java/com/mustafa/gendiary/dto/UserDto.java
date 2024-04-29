package com.mustafa.gendiary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String username;
    private String email;
    private String gender;
    private String country;
    private Date birthDate;
    private Date joinDate;
    private String password;
}
