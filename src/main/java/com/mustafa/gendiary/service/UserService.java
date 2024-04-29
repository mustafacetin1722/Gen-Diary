package com.mustafa.gendiary.service;

import com.mustafa.gendiary.beans.AuthenticationResponse;
import com.mustafa.gendiary.dto.AuthenticationRequest;
import com.mustafa.gendiary.dto.UserDto;
import com.mustafa.gendiary.model.User;

import java.util.List;

public interface UserService {
    User getDbUserById(Long userId);
    List<UserDto> getAllUser();
    UserDto getUserById(Long Id);
    AuthenticationResponse createUser(UserDto userDto);
    String updateUser(Long id,UserDto userDto);
    String deleteUser(Long id);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void followUser(Long Id);
    void unfollowUser(Long Id);
    User getAuthenticatedUser();
}
