package com.javacodeex.u_future.service;

import com.javacodeex.u_future.domain.converter.SensorConverter;
import com.javacodeex.u_future.domain.dto.SensorDTO;
import com.javacodeex.u_future.domain.entity.Sensor;
import com.javacodeex.u_future.exception.SensorNotFoundException;
import com.javacodeex.u_future.repository.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SensorServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @Mock
    private SensorConverter sensorConverter;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private SensorService sensorService;

    private Sensor sensor;
    private SensorDTO sensorDTO;

    @BeforeEach
    void setUp() {
        sensor = new Sensor();
        sensor.setName("Temperature Sensor");

        sensorDTO = new SensorDTO();
        sensorDTO.setName("Temperature Sensor");
    }

    @Test
    void create_SensorDoesNotExist_SavesSensor() {
        when(sensorConverter.toEntity(sensorDTO)).thenReturn(sensor);
        when(sensorRepository.findSensorByName("Temperature Sensor")).thenReturn(Optional.empty());

        sensorService.create(sensorDTO, bindingResult);

        verify(sensorRepository, times(1)).save(sensor);
    }

    @Test
    void create_SensorAlreadyExists_ThrowsException() {
        when(sensorConverter.toEntity(sensorDTO)).thenReturn(sensor);
        when(sensorRepository.findSensorByName("Temperature Sensor")).thenReturn(Optional.of(sensor));

        assertThrows(SensorNotFoundException.class, () -> sensorService.create(sensorDTO, bindingResult));
    }

    @Test
    void findOneByNameOrElseThrowException_SensorExists_ReturnsSensor() {
        when(sensorRepository.findSensorByName("Temperature Sensor")).thenReturn(Optional.of(sensor));

        Sensor foundSensor = sensorService.findOneByNameOrElseThrowException("Temperature Sensor");

        assertNotNull(foundSensor);
        assertEquals("Temperature Sensor", foundSensor.getName());
    }

    @Test
    void findOneByNameOrElseThrowException_SensorNotFound_ThrowsException() {
        when(sensorRepository.findSensorByName("Unknown Sensor")).thenReturn(Optional.empty());

        assertThrows(SensorNotFoundException.class, () -> sensorService.findOneByNameOrElseThrowException("Unknown Sensor"));
    }
}