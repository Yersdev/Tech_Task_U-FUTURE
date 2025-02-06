package com.javacodeex.u_future.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO для представления сенсора в системе.
 * Содержит данные о названии сенсора.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для представления сенсора")
public class SensorDTO {

    /**
     * Имя сенсора. Должно содержать от 3 до 30 символов.
     *
     * @param name имя сенсора.
     * @return имя сенсора.
     */
    @NotEmpty(message = "Имя сенсора не должно быть пустым!")
    @Size(min = 3, max = 30, message = "Имя сенсора должно содержать от 3 до 30 символов")
    @Schema(description = "Имя сенсора", example = "Температурный датчик")
    private String name;
}
