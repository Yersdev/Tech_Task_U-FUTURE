package com.javacodeex.u_future.exception;

/**
 * Исключение, возникающее при попытке создания измерения, которое не удалось создать.
 * Это исключение может быть выброшено, когда система не может создать измерение по какой-либо причине.
 */
public class MeasurementHasNotCreatedException extends RuntimeException {

    /**
     * Конструктор для создания исключения с сообщением.
     *
     * @param message Сообщение об ошибке, которое будет отображено.
     */
    public MeasurementHasNotCreatedException(String message) {
        super(message);
    }
}
