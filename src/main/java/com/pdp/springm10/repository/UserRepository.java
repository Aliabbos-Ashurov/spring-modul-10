package com.pdp.springm10.repository;

import com.pdp.springm10.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(@NonNull String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}