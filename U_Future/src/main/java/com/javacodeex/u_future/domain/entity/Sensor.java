package com.javacodeex.u_future.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Сущность для представления сенсора в системе.
 * Содержит информацию о сенсоре, его имени и связанных с ним измерениях.
 */
@Entity
@Table(name = "sensor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {

    /**
     * Уникальный идентификатор сенсора.
     *
     * @param id идентификатор сенсора.
     * @return идентификатор сенсора.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Имя сенсора. Должно содержать от 3 до 30 символов.
     * Имя сенсора должно быть уникальным.
     *
     * @param name имя сенсора.
     * @return имя сенсора.
     */
    @Size(min = 3, max = 30, message = "Имя сенсора должно содержать от 3 до 30 символов")
    @NotEmpty(message = "Имя сенсора не должно быть пустым!")
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Список измерений, связанных с данным сенсором.
     *
     * @param measurements список измерений.
     * @return список измерений.
     */
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;
}
