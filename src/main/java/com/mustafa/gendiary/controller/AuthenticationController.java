package com.mustafa.gendiary.controller;

import com.mustafa.gendiary.beans.AuthenticationResponse;
import com.mustafa.gendiary.dto.AuthenticationRequest;
import com.mustafa.gendiary.dto.UserDto;
import com.mustafa.gendiary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registration(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request){
        return new ResponseEntity<>(userService.authenticate(request),HttpStatus.OK);
    }
}
