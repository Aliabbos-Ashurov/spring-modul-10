package com.pdp.springm10.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.springm10.dto.ErrorMessageDTO;
import jakarta.servlet.ServletOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:30
 **/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .requestMatchers("/api/auth/token", "/api/auth/register", "/api/auth/refresh").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandlingConf -> {
                            exceptionHandlingConf.accessDeniedHandler(accessDeniedHandler());
                            exceptionHandlingConf.authenticationEntryPoint(authenticationEntryPoint());
                        }
                )
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            accessDeniedException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = accessDeniedException.getMessage();
            ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.of("403", errorMessage, errorPath);
            response.setStatus(404);
            ServletOutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, errorMessageDTO);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            authException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = authException.getMessage();
            ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.of("401", errorMessage, errorPath);
            response.setStatus(401);
            ServletOutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, errorMessageDTO);
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }
}
