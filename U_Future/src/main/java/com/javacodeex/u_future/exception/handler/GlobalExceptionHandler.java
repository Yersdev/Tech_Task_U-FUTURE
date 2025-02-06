package com.javacodeex.u_future.exception.handler;

import com.javacodeex.u_future.domain.dto.response.error.ResponseForError;
import com.javacodeex.u_future.exception.MeasurementHasNotCreatedException;
import com.javacodeex.u_future.exception.SensorHasNotCreatedException;
import com.javacodeex.u_future.exception.SensorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений для системы.
 * Обрабатывает исключения, возникающие в различных слоях приложения, и возвращает соответствующие ответы.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение, связанное с невозможностью создания измерения.
     *
     * @param exception исключение MeasurementHasNotCreatedException.
     * @return ответ с ошибкой и кодом состояния HTTP.
     */
    @ExceptionHandler
    private ResponseEntity<ResponseForError> handleException(MeasurementHasNotCreatedException exception) {
        ResponseForError response = new ResponseForError(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает исключение, когда сенсор не найден в базе данных.
     *
     * @return ответ с ошибкой и кодом состояния HTTP.
     */
    @ExceptionHandler(SensorNotFoundException.class)
    private ResponseEntity<ResponseForError> handleException() {
        ResponseForError response = new ResponseForError(
                "Db does not have this sensor",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает исключение, связанное с невозможностью создания сенсора.
     *
     * @param exception исключение SensorHasNotCreatedException.
     * @return ответ с ошибкой и кодом состояния HTTP.
     */
    @ExceptionHandler(SensorHasNotCreatedException.class)
    private ResponseEntity<ResponseForError> handleException(SensorHasNotCreatedException exception) {
        ResponseForError response = new ResponseForError(
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
