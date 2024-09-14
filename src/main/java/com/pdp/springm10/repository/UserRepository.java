package com.pdp.springm10.repository;

import com.pdp.springm10.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(@NonNull String username);

    boolean existsByUsername(String username);
}