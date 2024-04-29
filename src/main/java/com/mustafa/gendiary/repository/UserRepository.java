package com.mustafa.gendiary.repository;

import com.mustafa.gendiary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUuid(String uuid);
    User findByEmail(String email);
}
