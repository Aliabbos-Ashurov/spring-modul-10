package com.pdp.springm10.repository;

import com.pdp.springm10.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  16:19
 **/
public interface TokensRepository extends JpaRepository<Tokens, Long> {
    Tokens findByUsername(String username);
    List<Tokens> findAllByUsername(String username);
}
