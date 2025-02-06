package com.javacodeex.u_future.controller;

import com.javacodeex.u_future.domain.dto.MeasurementDto;
import com.javacodeex.u_future.domain.dto.response.RainyDaysCountDTO;
import com.javacodeex.u_future.service.MeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления измерениями. Включает методы для получения всех измерений,
 * получения количества дождливых дней и добавления новых измерений.
 */
@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
@Tag(name = "Measurements", description = "Управление измерениями")
public class MeasurementsController {

    private final MeasurementService measurementService;

    /**
     * Метод для получения всех измерений, зарегистрированных в системе.
     *
     * @return ResponseEntity с успешным ответом и списком всех измерений в формате JSON
     */
    @Operation(summary = "Получить все измерения", description = "Возвращает список всех измерений, зарегистрированных в системе")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка измерений")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MeasurementDto>> getMeasurements() {
        return ResponseEntity.ok(measurementService.findAll());
    }

    /**
     * Метод для получения количества дождливых дней, когда был зафиксирован дождь.
     *
     * @return ResponseEntity с успешным ответом и количеством дождливых дней в формате JSON
     */
    @Operation(summary = "Получить количество дождливых дней", description = "Возвращает количество дней, когда был дождь")
    @ApiResponse(responseCode = "200", description = "Успешное получение количества дождливых дней")
    @GetMapping(value = "/rainyDaysCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RainyDaysCountDTO> showRainyDays() {
        return ResponseEntity.ok(measurementService.getRainingMeasurementsCount());
    }

    /**
     * Метод для добавления нового измерения. Измерение включает температуру в диапазоне от -80 до +80 градусов
     * и информацию о статусе дождя.
     *
     * @param measurementDTO объект, содержащий данные о температуре и осадках
     * @param bindingResult результат валидации входных данных
     * @return ResponseEntity с HTTP статусом 200 в случае успешного добавления
     */
    @Operation(summary = "Добавление измерения", description = "Добавляет новое измерение с температурой в диапазоне от -80 до +80 градусов и статусом дождя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Измерение успешно добавлено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
    })
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(
            @Parameter(description = "DTO измерения с данными о температуре, осадках и сенсоре")
            @RequestBody @Valid MeasurementDto measurementDTO,
            BindingResult bindingResult) {
        measurementService.addMeasurement(measurementDTO, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
