package com.pdp.springm10.repository;

import com.pdp.springm10.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
}