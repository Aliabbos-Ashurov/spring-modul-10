package com.pdp.springm10.controller;

import com.pdp.springm10.dto.RefreshTokenRequestDTO;
import com.pdp.springm10.dto.TokenRequestDTO;
import com.pdp.springm10.dto.TokensResponseDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.Tokens;
import com.pdp.springm10.service.TokensService;
import com.pdp.springm10.service.UserService;
import com.pdp.springm10.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:01
 **/
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokensService tokensService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<TokensResponseDTO> getToken(@RequestBody TokenRequestDTO dto) {
        String username = dto.username();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authenticationManager.authenticate(authenticationToken);
        String accessToken = jwtTokenUtil.generateAccessToken(username);
        String refreshToken = jwtTokenUtil.generateRefreshToken(username);
        tokensService.saveTokens(username, accessToken, refreshToken);
        return ResponseEntity.ok(new TokensResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        userService.save(dto);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokensResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO dto) {
        String refreshToken = dto.refreshToken();
        if (jwtTokenUtil.isValid(refreshToken)) {
            String username = JwtTokenUtil.getUsername(refreshToken);
            String newAccessToken = jwtTokenUtil.generateAccessToken(username);
            String newRefreshToken = jwtTokenUtil.generateRefreshToken(username);
            tokensService.deleteTokensByUsername(username);
            tokensService.saveTokens(username, newAccessToken, newRefreshToken);
            return ResponseEntity.ok(new TokensResponseDTO(newAccessToken, newRefreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
