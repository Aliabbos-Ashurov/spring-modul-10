package com.pdp.springm10.repository;

import com.pdp.springm10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:34
 **/
public interface UserRepository extends JpaRepository<User, Integer> {
}
