package com.javacodeex.u_future.domain.converter;

import com.javacodeex.u_future.domain.dto.MeasurementDto;
import com.javacodeex.u_future.domain.entity.Measurement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Конвертер для преобразования между сущностью {@link Measurement} и DTO {@link MeasurementDto}.
 * Используется {@link ModelMapper} для выполнения маппинга объектов.
 */
@Component
@RequiredArgsConstructor
public class MeasurementConverter {

    private final ModelMapper modelMapper;

    /**
     * Преобразует сущность {@link Measurement} в объект {@link MeasurementDto}.
     *
     * @param measurement объект сущности, который нужно преобразовать в DTO
     * @return преобразованный объект {@link MeasurementDto}
     */
    public MeasurementDto toDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDto.class);
    }

    /**
     * Преобразует объект {@link MeasurementDto} в сущность {@link Measurement}.
     *
     * @param measurementDTO объект DTO, который нужно преобразовать в сущность
     * @return преобразованный объект {@link Measurement}
     */
    public Measurement toEntity(MeasurementDto measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
