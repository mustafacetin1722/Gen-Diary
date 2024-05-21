package com.mustafa.gendiary.service.impl;

import com.mustafa.gendiary.enums.UserRole;
import com.mustafa.gendiary.model.User;
import com.mustafa.gendiary.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class UserCredentialsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserCredentialsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password ");
        }
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(),user.getPassword(), mapRolesToAuthorities(Collections.singleton(user.getRole())));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
