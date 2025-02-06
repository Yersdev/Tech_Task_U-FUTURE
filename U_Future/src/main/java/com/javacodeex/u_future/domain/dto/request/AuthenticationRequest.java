package com.javacodeex.u_future.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для запроса аутентификации пользователя, содержащий имя пользователя и пароль.
 * Используется для передачи данных в процессе аутентификации.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для запроса аутентификации")
public class AuthenticationRequest {

    @Schema(description = "Имя пользователя", example = "john_doe")
    private String username;

    @Schema(description = "Пароль пользователя", example = "securePassword123")
    private String password;
}
