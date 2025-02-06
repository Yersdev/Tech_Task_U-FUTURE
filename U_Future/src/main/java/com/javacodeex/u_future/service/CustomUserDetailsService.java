package com.javacodeex.u_future.service;

import com.javacodeex.u_future.domain.dto.UserDto;
import com.javacodeex.u_future.domain.entity.User;
import com.javacodeex.u_future.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Сервис для работы с пользователями, реализующий интерфейс {@link UserDetailsService}.
 * <p>Этот сервис предоставляет методы для загрузки данных пользователя по имени пользователя и
 * регистрации нового пользователя.</p>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Загружает данные пользователя по имени пользователя.
     *
     * @param username имя пользователя.
     * @return объект {@link UserDetails}, содержащий информацию о пользователе.
     * @throws UsernameNotFoundException если пользователь не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, new ArrayList<>());
    }

    /**
     * Регистрирует нового пользователя.
     * <p>Если пользователь с таким именем уже существует, будет выброшено исключение.</p>
     *
     * @param userDTO объект {@link UserDto}, содержащий данные для регистрации.
     * @throws RuntimeException если имя пользователя уже существует.
     */
    @Transactional
    public void registerUser(UserDto userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = convertToEntity(userDTO);
        userRepository.save(user);
    }

    /**
     * Конвертирует объект {@link UserDto} в сущность {@link User}.
     *
     * @param userDTO объект DTO с данными пользователя.
     * @return объект сущности {@link User}.
     */
    private User convertToEntity(UserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(true);
        return user;
    }
}
