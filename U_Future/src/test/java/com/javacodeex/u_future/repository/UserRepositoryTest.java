package com.javacodeex.u_future.repository;

import com.javacodeex.u_future.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
    }

    @Test
    void findByUsername_UserExists_ReturnsUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void findByUsername_UserNotFound_ReturnsEmpty() {
        when(userRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepository.findByUsername("unknownuser");

        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findByUsername("unknownuser");
    }
}
