package com.pdp.springm10.controller;

import com.pdp.springm10.dto.RefreshTokenRequestDTO;
import com.pdp.springm10.dto.TokenRequestDTO;
import com.pdp.springm10.dto.TokensResponseDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.service.TokensService;
import com.pdp.springm10.service.UserService;
import com.pdp.springm10.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Operation(summary = "Generate access and refresh tokens", description = "Authenticates the user and generates a pair of JWT tokens (access and refresh).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tokens generated successfully",
                    content = @Content(schema = @Schema(implementation = TokensResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid username or password"),
    })
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

    @Operation(summary = "Register a new user", description = "Creates a new user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        userService.save(dto);
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "Refresh tokens", description = "Generates new access and refresh tokens using the provided refresh token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tokens refreshed successfully",
                    content = @Content(schema = @Schema(implementation = TokensResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token")
    })
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
