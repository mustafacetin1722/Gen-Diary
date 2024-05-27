package com.mustafa.gendiary.service.impl;

import com.mustafa.gendiary.beans.AuthenticationResponse;
import com.mustafa.gendiary.dto.AuthenticationRequest;
import com.mustafa.gendiary.dto.UserDto;
import com.mustafa.gendiary.enums.UserRole;
import com.mustafa.gendiary.model.User;
import com.mustafa.gendiary.repository.UserRepository;
import com.mustafa.gendiary.security.JwtService;
import com.mustafa.gendiary.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User getDbUserById(Long userId)  {
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(
                user -> UserDto.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .avatarUrl(user.getAvatarUrl())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .gender(user.getGender())
                        .email(user.getEmail())
                        .birthDate(user.getBirtDate())
                        .joinDate(user.getJoinDate())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long Id) {
        Optional<User> user = userRepository.findById(Id);
        return user.map(x -> UserDto.builder()
                .firstName(x.getFirstName())
                .lastName(x.getLastName())
                .avatarUrl(x.getAvatarUrl())
                .username(x.getUsername())
                .email(x.getEmail())
                .gender(x.getGender())
                .email(x.getEmail())
                .birthDate(x.getBirtDate())
                .joinDate(x.getJoinDate())
                .build()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AuthenticationResponse createUser(UserDto userDto) {
        var dbUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .avatarUrl(userDto.getAvatarUrl())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .country(userDto.getCountry())
                .birtDate(userDto.getBirthDate())
                .joinDate(userDto.getJoinDate())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .uuid(UUID.randomUUID().toString())
                .role(UserRole.USER)
                .build();
        userRepository.save(dbUser);
        String token = jwtService.generateToken(dbUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public String updateUser(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        user.get().setFirstName(userDto.getFirstName());
        user.get().setLastName(userDto.getLastName());
        user.get().setAvatarUrl(userDto.getAvatarUrl());
        user.get().setUsername(userDto.getUsername());
        user.get().setEmail(userDto.getEmail());
        user.get().setGender(userDto.getGender());
        user.get().setCountry(userDto.getCountry());
        user.get().setBirtDate(userDto.getBirthDate());
        user.get().setJoinDate(userDto.getJoinDate());
        userRepository.save(user.get());
        return "Successfully";
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        userRepository.delete(user.get());
        return "Successfully";
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail());
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public void followUser(Long Id) {
        User  authUser = getAuthenticatedUser();
        if (!authUser.getId().equals(Id)){
            Optional<User> userToFollow = userRepository.findById(Id);
            authUser.getFollowerUsers().add(userToFollow.get());
            authUser.setFollowerCount(authUser.getFollowingCount()+1);
            userToFollow.get().getFollowerUsers().add(authUser);
            userToFollow.get().setFollowerCount(authUser.getFollowerCount()+1);
            userRepository.save(userToFollow.get());
            userRepository.save(authUser);
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void unfollowUser(Long Id) {
        User  authUser = getAuthenticatedUser();
        if (!authUser.getId().equals(Id)){
            Optional<User> userToFollow = userRepository.findById(Id);
            authUser.getFollowerUsers().remove(userToFollow.get());
            authUser.setFollowerCount(authUser.getFollowingCount()+1);
            userToFollow.get().getFollowerUsers().remove(authUser);
            userToFollow.get().setFollowerCount(authUser.getFollowerCount()+1);
            userRepository.save(userToFollow.get());
            userRepository.save(authUser);
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User getAuthenticatedUser() {
        String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return getUserByEmail(authUserEmail);
    }
}
