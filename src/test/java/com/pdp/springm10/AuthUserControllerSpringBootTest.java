package com.pdp.springm10;

import com.pdp.springm10.controller.AuthUserController;
import com.pdp.springm10.dto.AuthUserCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringJUnitConfig
public class AuthUserControllerSpringBootTest {

    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void testCreateUser() {
        AuthUserCreateDTO createDTO = new AuthUserCreateDTO("a", "a", "a¬");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<AuthUserCreateDTO> request = new HttpEntity<>(createDTO, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/authuser", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Successfully Created - User");
    }

    @Test
    public void testDeleteUser() {
        String userId = "1";
        restTemplate.delete("/api/authuser/" + userId);
    }
}

