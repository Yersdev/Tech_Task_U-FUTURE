package com.javacodeex.u_future.service;

import com.javacodeex.u_future.domain.converter.MeasurementConverter;
import com.javacodeex.u_future.domain.dto.MeasurementDto;
import com.javacodeex.u_future.domain.dto.response.RainyDaysCountDTO;
import com.javacodeex.u_future.domain.entity.Measurement;
import com.javacodeex.u_future.repository.MeasurementRepository;
import com.javacodeex.u_future.utils.BindCheckerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с измерениями.
 * <p>Предоставляет методы для добавления измерений, получения всех измерений и подсчета
 * количества дождливых дней.</p>
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepo;
    private final SensorService sensorService;
    private final MeasurementConverter measurementConverter;

    /**
     * Получает список всех измерений.
     *
     * @return список объектов {@link MeasurementDto}, представляющих все измерения.
     */
    public List<MeasurementDto> findAll() {
        return measurementRepo.findAll()
                .stream()
                .map(measurementConverter::toDTO)
                .toList();
    }

    /**
     * Получает количество дождливых дней.
     *
     * @return объект {@link RainyDaysCountDTO}, содержащий количество дождливых дней.
     */
    public RainyDaysCountDTO getRainingMeasurementsCount() {
        return RainyDaysCountDTO.builder()
                .rainyDaysCount(measurementRepo.countRainingMeasurements())
                .build();
    }

    /**
     * Добавляет новое измерение в систему.
     * <p>Метод выполняет проверку на ошибки валидации, затем создает и сохраняет объект {@link Measurement}.
     * Связывает его с сенсором, указанным в {@link MeasurementDto}, и сохраняет в базе данных.</p>
     *
     * @param measurementDto объект {@link MeasurementDto}, содержащий данные измерения.
     * @param bindingResult объект {@link BindingResult}, который содержит информацию о возможных ошибках валидации.
     * @throws RuntimeException если есть ошибки валидации.
     */
    @Transactional
    public void addMeasurement(MeasurementDto measurementDto, BindingResult bindingResult){
        BindCheckerUtil.checkBind(bindingResult);
        Measurement measurement = measurementConverter.toEntity(measurementDto);
        measurement.setValue(measurementDto.getValue());
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensorService.findOneByNameOrElseThrowException(measurementDto.getSensor().getName()));
        measurement.setRaining(measurementDto.getRaining());
        measurementRepo.save(measurement);
    }
}
