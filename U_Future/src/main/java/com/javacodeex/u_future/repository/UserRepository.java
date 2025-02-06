package com.javacodeex.u_future.repository;

import com.javacodeex.u_future.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link User}.
 * <p>Этот интерфейс предоставляет методы для выполнения CRUD операций с таблицей
 * {@code users} в базе данных, а также предоставляет метод для поиска пользователя по имени.</p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Метод для поиска пользователя по его имени.
     *
     * @param username имя пользователя.
     * @return {@link Optional} содержащий найденного пользователя, если он существует, иначе пустой {@link Optional}.
     */
    Optional<User> findByUsername(String username);
}
