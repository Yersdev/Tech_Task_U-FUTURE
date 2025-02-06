package com.javacodeex.u_future.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO для представления измерений сенсора. Содержит данные о температуре, флаге дождя и связанном сенсоре.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "DTO для представления измерений сенсора")
public class MeasurementDto {

    /**
     * Значение температуры, которое должно быть в пределах от -80 до +80 градусов.
     *
     * @param value значение температуры.
     * @return значение температуры.
     */
    @NotNull(message = "Should not be empty")
    @Max(value = 80, message = "the temperature value should be less than 80")
    @Min(value = -80, message = "the temperature value should be higher than 80")
    @Schema(description = "Значение температуры", example = "23.5")
    private Double value;

    /**
     * Флаг дождя, где true - идет дождь, false - нет дождя.
     *
     * @param raining флаг дождя.
     * @return флаг дождя.
     */
    @NotNull(message = "Only true or false")
    @Schema(description = "Флаг дождя (true - идет дождь, false - нет дождя)", example = "false")
    private Boolean raining;

    /**
     * Объект сенсора, связанный с данным измерением.
     *
     * @param sensor сенсор, который сделал это измерение.
     * @return объект сенсора.
     */
    @NotNull(message = "Sensor should be here")
    @Schema(description = "Объект сенсора, связанный с измерением")
    private SensorDTO sensor;
}
