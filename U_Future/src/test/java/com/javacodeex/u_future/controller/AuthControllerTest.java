package com.javacodeex.u_future.controller;

import com.javacodeex.u_future.domain.dto.UserDto;
import com.javacodeex.u_future.domain.dto.request.AuthenticationRequest;
import com.javacodeex.u_future.service.CustomUserDetailsService;
import com.javacodeex.u_future.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void createToken_ValidInput_ReturnsJwtToken() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        String token = "mocked-jwt-token";

        when(jwtUtil.generateToken(request.getUsername())).thenReturn(token);

        mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(token));

        verify(userDetailsService, times(1)).loadUserByUsername(request.getUsername());
        verify(jwtUtil, times(1)).generateToken(request.getUsername());
    }

    @Test
    void register_ValidUser_ReturnsOk() throws Exception {
        UserDto userDto = new UserDto();
        doNothing().when(customUserDetailsService).registerUser(any(UserDto.class));

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());

        verify(customUserDetailsService, times(1)).registerUser(any(UserDto.class));
    }
}
