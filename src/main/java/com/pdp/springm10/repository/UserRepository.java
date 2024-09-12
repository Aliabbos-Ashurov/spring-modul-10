package com.pdp.springm10.repository;

import com.pdp.springm10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}