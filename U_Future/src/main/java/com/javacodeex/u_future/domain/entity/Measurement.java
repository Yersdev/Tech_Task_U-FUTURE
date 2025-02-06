package com.javacodeex.u_future.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Сущность для представления измерений, полученных от сенсоров.
 * Содержит информацию о температуре, осадках, связанном сенсоре и времени создания.
 */
@Entity
@Table(name = "measurement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    /**
     * Уникальный идентификатор измерения.
     *
     * @param id идентификатор измерения.
     * @return идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Значение температуры, которое должно быть в пределах от -80 до +80 градусов.
     *
     * @param value значение температуры.
     * @return значение температуры.
     */
    @NotNull(message = "Should not be empty")
    @Max(value = 80, message = "The temperature value should be less than or equal to 80")
    @Min(value = -80, message = "The temperature value should be greater than or equal to -80")
    @Column(nullable = false, length = 20)
    private Double value;

    /**
     * Флаг дождя, где true - идет дождь, false - нет дождя.
     *
     * @param raining флаг дождя.
     * @return флаг дождя.
     */
    @Column(nullable = false, length = 20)
    private Boolean raining;

    /**
     * Сенсор, который сделал это измерение.
     *
     * @param sensor объект сенсора.
     * @return сенсор, связанный с измерением.
     */
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    /**
     * Время, когда было создано измерение.
     *
     * @param createdAt время создания измерения.
     * @return время создания.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
