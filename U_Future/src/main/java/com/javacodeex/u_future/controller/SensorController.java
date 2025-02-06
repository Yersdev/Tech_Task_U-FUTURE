package com.javacodeex.u_future.controller;

import com.javacodeex.u_future.domain.dto.SensorDTO;
import com.javacodeex.u_future.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления сенсорами. Включает метод для регистрации новых сенсоров в системе.
 */
@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
@Tag(name = "Sensors", description = "Управление сенсорами")
public class SensorController {

    private final SensorService sensorService;

    /**
     * Метод для регистрации нового сенсора в системе. Сенсор создается на основе данных, переданных в DTO.
     *
     * @param sensorDTO объект, содержащий данные сенсора, такие как название
     * @param bindingResult результат валидации входных данных
     * @return ResponseEntity с HTTP статусом 200, если сенсор успешно зарегистрирован
     */
    @Operation(summary = "Регистрация нового сенсора", description = "Создает новый сенсор и сохраняет его в системе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сенсор успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
    })
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(
            @Parameter(description = "DTO сенсора с его названием")
            @RequestBody @Valid SensorDTO sensorDTO,
            BindingResult bindingResult) {
        sensorService.create(sensorDTO, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
