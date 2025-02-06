package com.javacodeex.u_future.exception;

/**
 * Исключение, которое выбрасывается, когда сенсор не может быть создан.
 *
 * Этот класс расширяет RuntimeException и используется для обработки ситуаций,
 * когда создание сенсора не удалось.
 */
public class SensorHasNotCreatedException extends RuntimeException {

    /**
     * Конструктор исключения, принимающий сообщение ошибки.
     *
     * @param message сообщение, которое будет передано в исключение.
     */
    public SensorHasNotCreatedException(String message) {
        super(message);
    }
}
