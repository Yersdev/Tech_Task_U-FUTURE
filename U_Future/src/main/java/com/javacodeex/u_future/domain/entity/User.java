package com.javacodeex.u_future.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность для представления пользователя в системе.
 * Содержит информацию о пользователе, включая имя пользователя, пароль и статус активности.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**
     * Уникальный идентификатор пользователя.
     *
     * @param id идентификатор пользователя.
     * @return идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    /**
     * Статус активности пользователя. Если значение true, то пользователь активен.
     *
     * @param enabled статус активности пользователя.
     * @return статус активности.
     */
    private boolean enabled;
}
