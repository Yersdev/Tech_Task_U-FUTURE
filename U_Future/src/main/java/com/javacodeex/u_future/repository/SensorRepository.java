package com.javacodeex.u_future.repository;

import com.javacodeex.u_future.domain.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link Sensor}.
 * <p>Этот интерфейс предоставляет методы для выполнения CRUD операций с таблицей
 * {@code sensor} в базе данных, а также предоставляет метод для поиска сенсора по имени.</p>
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    /**
     * Метод для поиска сенсора по его имени.
     *
     * @param sensorName имя сенсора.
     * @return {@link Optional} содержащий найденный сенсор, если он существует, иначе пустой {@link Optional}.
     */
    Optional<Sensor> findSensorByName(String sensorName);
}
