package com.pdp.springm10;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.springm10.controller.AuthUserController;
import com.pdp.springm10.dto.AuthUserCreateDTO;
import com.pdp.springm10.service.AuthUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthUserController.class)
public class AuthUserControllerWebMvcTest {

    @MockBean
    private AuthUserService authUserService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateUser() throws Exception {
        AuthUserCreateDTO dto = new AuthUserCreateDTO("Test User", null, null);
        doNothing().when(authUserService).create(any(AuthUserCreateDTO.class));

        mockMvc.perform(post("/auth/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

