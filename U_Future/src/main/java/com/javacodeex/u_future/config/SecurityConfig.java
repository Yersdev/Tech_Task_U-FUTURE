package com.javacodeex.u_future.config;

import com.javacodeex.u_future.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурация безопасности для приложения. Настроены фильтры безопасности, аутентификация и шифрование паролей.
 * Этот класс устанавливает политики безопасности, такие как использование JWT для авторизации.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Создает и настраивает SecurityFilterChain для защиты веб-запросов.
     * Настроена аутентификация с использованием JWT токенов, а также разрешение доступа к определенным URL без аутентификации.
     *
     * @param http объект HttpSecurity для конфигурации безопасности
     * @return сконфигурированный SecurityFilterChain
     * @throws Exception в случае ошибки настройки безопасности
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Initializing SecurityFilterChain");

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/sign-in").permitAll()
                        .requestMatchers("/auth/sign-up").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Создает и возвращает объект AuthenticationManager для обработки аутентификации пользователей.
     *
     * @param authenticationConfiguration конфигурация аутентификации
     * @return объект AuthenticationManager
     * @throws Exception в случае ошибки создания AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Создает и возвращает объект PasswordEncoder, использующий BCrypt для шифрования паролей.
     *
     * @return объект PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
