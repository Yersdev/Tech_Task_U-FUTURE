package com.javacodeex.u_future.domain.converter;

import com.javacodeex.u_future.domain.dto.SensorDTO;
import com.javacodeex.u_future.domain.entity.Sensor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования между сущностью {@link Sensor} и DTO {@link SensorDTO}.
 * Используется {@link ModelMapper} для выполнения маппинга объектов.
 */
@Component
@RequiredArgsConstructor
public class SensorConverter {

    private final ModelMapper modelMapper;

    /**
     * Преобразует объект {@link SensorDTO} в сущность {@link Sensor}.
     *
     * @param sensorDTO объект DTO, который нужно преобразовать в сущность
     * @return преобразованный объект {@link Sensor}
     */
    public Sensor toEntity(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    /**
     * Преобразует сущность {@link Sensor} в объект {@link SensorDTO}.
     *
     * @param sensor объект сущности, который нужно преобразовать в DTO
     * @return преобразованный объект {@link SensorDTO}
     */
    public SensorDTO toDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
