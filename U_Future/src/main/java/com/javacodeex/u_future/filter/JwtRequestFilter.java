package com.javacodeex.u_future.filter;

import com.javacodeex.u_future.service.CustomUserDetailsService;
import com.javacodeex.u_future.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Фильтр, который перехватывает все входящие HTTP-запросы для проверки и аутентификации
 * пользователя с помощью JWT (JSON Web Token).
 *
 * <p>Фильтр проверяет наличие токена в заголовке "Authorization" и, если токен действителен,
 * устанавливает аутентификацию в контексте безопасности.</p>
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Конструктор для инициализации JwtRequestFilter с необходимыми зависимостями.
     *
     * @param jwtUtil необходим для работы с JWT.
     * @param customUserDetailsService необходим для загрузки пользовательских данных.
     */
    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Метод, который выполняет фильтрацию запросов, проверяя наличие и валидность JWT в заголовке.
     * Если токен действителен, аутентификация устанавливается в SecurityContext.
     *
     * @param request HTTP-запрос.
     * @param response HTTP-ответ.
     * @param chain цепочка фильтров.
     * @throws ServletException если произошла ошибка сервлета.
     * @throws IOException если произошла ошибка ввода/вывода.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwtToken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, username)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
