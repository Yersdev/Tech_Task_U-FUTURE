package com.javacodeex.u_future.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для представления данных пользователя.
 * Содержит имя пользователя и пароль для аутентификации.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * Имя пользователя.
     *
     * @param username имя пользователя.
     * @return имя пользователя.
     */
    private String username;

    /**
     * Пароль пользователя.
     *
     * @param password пароль пользователя.
     * @return пароль пользователя.
     */
    private String password;
}
