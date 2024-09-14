package com.pdp.springm10.service;

import com.pdp.springm10.entity.Tokens;
import com.pdp.springm10.repository.TokensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  16:20
 **/
@Service
@RequiredArgsConstructor
public class TokensService {

    private final TokensRepository tokensRepository;

    @Transactional
    public Tokens saveTokens(String username, String accessToken, String refreshToken) {
        Tokens tokens = new Tokens(null, accessToken, refreshToken, username);
        return tokensRepository.save(tokens);
    }

    public Tokens findByUsername(String username) {
        return tokensRepository.findByUsername(username);
    }

    @Transactional
    public void deleteTokensByUsername(String username) {
        List<Tokens> tokens = tokensRepository.findAllByUsername(username);
        tokensRepository.deleteAll(tokens);
    }
}
