package com.mustafa.gendiary.service.impl;

import com.mustafa.gendiary.beans.AuthenticationResponse;
import com.mustafa.gendiary.dto.AuthenticationRequest;
import com.mustafa.gendiary.dto.UserDto;
import com.mustafa.gendiary.model.User;
import com.mustafa.gendiary.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User getDbUserById(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }

    @Override
    public UserDto getUserById(Long Id) {
        return null;
    }

    @Override
    public AuthenticationResponse createUser(UserDto userDto) {
        return null;
    }

    @Override
    public String updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }

    @Override
    public void followUser(Long Id) {

    }

    @Override
    public void unfollowUser(Long Id) {

    }

    @Override
    public User getAuthenticatedUser() {
        return null;
    }
}
