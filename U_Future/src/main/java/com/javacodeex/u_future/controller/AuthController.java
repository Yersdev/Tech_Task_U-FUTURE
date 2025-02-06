package com.javacodeex.u_future.controller;

import com.javacodeex.u_future.domain.dto.UserDto;
import com.javacodeex.u_future.domain.dto.request.AuthenticationRequest;
import com.javacodeex.u_future.domain.dto.response.AuthenticationResponse;
import com.javacodeex.u_future.service.CustomUserDetailsService;
import com.javacodeex.u_future.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для обработки запросов, связанных с аутентификацией и регистрацией пользователей.
 * Включает методы для регистрации пользователя и авторизации с помощью email и пароля.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Метод для авторизации пользователя. Возвращает JWT токен на основе введенных данных (email и пароля).
     *
     * @param request объект, содержащий email и пароль пользователя для аутентификации
     * @return ResponseEntity с JWT токеном в случае успешной аутентификации
     */
    @Operation(summary = "Авторизация", description = "Авторизация за счет введения email и пароля с бд. В моем исполнение проекта данные уже загружаются в бд.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно", content =
                    {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema =
                            @Schema(implementation = AuthenticationResponse.class)))})}
    )
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    /**
     * Метод для регистрации нового пользователя. Возвращает статус успешной регистрации.
     *
     * @param user объект, содержащий информацию о пользователе (email и пароль)
     * @return ResponseEntity с HTTP статусом 200, если регистрация прошла успешно
     */
    @Operation(summary = "Регистрация", description = "Регистрация за счет введения email и пароля.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно", content =
                    {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema =
                            @Schema(implementation = AuthenticationResponse.class)))})}
    )
    @PostMapping("/sign-up")
    public ResponseEntity<HttpStatus> register(@RequestBody UserDto user) {
        customUserDetailsService.registerUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
