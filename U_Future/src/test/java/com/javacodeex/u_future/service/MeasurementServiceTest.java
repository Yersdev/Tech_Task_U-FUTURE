package com.javacodeex.u_future.service;

import com.javacodeex.u_future.domain.converter.MeasurementConverter;
import com.javacodeex.u_future.domain.dto.MeasurementDto;
import com.javacodeex.u_future.domain.dto.SensorDTO;
import com.javacodeex.u_future.domain.dto.response.RainyDaysCountDTO;
import com.javacodeex.u_future.domain.entity.Measurement;
import com.javacodeex.u_future.domain.entity.Sensor;
import com.javacodeex.u_future.repository.MeasurementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepo;

    @Mock
    private SensorService sensorService;

    @Mock
    private MeasurementConverter measurementConverter;

    @InjectMocks
    private MeasurementService measurementService;

    @Test
    void findAll_ShouldReturnListOfMeasurementDtos() {
        Measurement measurement = new Measurement();
        MeasurementDto measurementDto = new MeasurementDto();
        when(measurementRepo.findAll()).thenReturn(Collections.singletonList(measurement));
        when(measurementConverter.toDTO(measurement)).thenReturn(measurementDto);

        List<MeasurementDto> result = measurementService.findAll();

        assertThat(result).isNotEmpty().contains(measurementDto);
        verify(measurementRepo, times(1)).findAll();
    }

    @Test
    void getRainingMeasurementsCount_ShouldReturnRainyDaysCountDTO() {
        when(measurementRepo.countRainingMeasurements()).thenReturn(5L);

        RainyDaysCountDTO result = measurementService.getRainingMeasurementsCount();

        assertThat(result).isNotNull();
        assertThat(result.getRainyDaysCount()).isEqualTo(5);
        verify(measurementRepo, times(1)).countRainingMeasurements();
    }

    @Test
    void addMeasurement_ShouldSaveMeasurement() {
        MeasurementDto measurementDto = new MeasurementDto();
        measurementDto.setValue(25.0);
        measurementDto.setRaining(true);
        Sensor sensor = new Sensor();
        SensorDTO sensorDTO = new SensorDTO();
        sensor.setName("Test Sensor");
        sensorDTO.setName("Test Sensor");

        measurementDto.setSensor(sensorDTO);

        Measurement measurement = new Measurement();
        when(measurementConverter.toEntity(measurementDto)).thenReturn(measurement);
        when(sensorService.findOneByNameOrElseThrowException("Test Sensor")).thenReturn(sensor);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        measurementService.addMeasurement(measurementDto, bindingResult);

        verify(measurementRepo, times(1)).save(any(Measurement.class));
    }
}
