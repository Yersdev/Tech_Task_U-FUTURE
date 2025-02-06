package com.javacodeex.u_future.repository;

import com.javacodeex.u_future.domain.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link Measurement}.
 * <p>Этот интерфейс предоставляет методы для выполнения CRUD операций с таблицей
 * {@code measurement} в базе данных, а также дополнительные методы для специфичных запросов.</p>
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    /**
     * Метод для подсчета количества измерений, где дождь идет (raining = true).
     *
     * @return количество измерений, в которых зафиксирован дождь.
     */
    @Query("SELECT COUNT(m) FROM Measurement m WHERE m.raining = true")
    long countRainingMeasurements();
}
