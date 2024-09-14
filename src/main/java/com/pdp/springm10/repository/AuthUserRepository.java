package com.pdp.springm10.repository;

import com.pdp.springm10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  13:09
 **/
public interface AuthUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a WHERE a.username = ?1")
    Optional<User> findByUsername(String username);
}
