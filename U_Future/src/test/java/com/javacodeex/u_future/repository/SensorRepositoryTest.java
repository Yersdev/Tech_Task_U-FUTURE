package com.javacodeex.u_future.repository;

import com.javacodeex.u_future.domain.entity.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SensorRepositoryTest {

    @Mock
    private SensorRepository sensorRepository;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Temperature Sensor");
    }

    @Test
    void findSensorByName_SensorExists_ReturnsSensor() {
        when(sensorRepository.findSensorByName("Temperature Sensor")).thenReturn(Optional.of(sensor));

        Optional<Sensor> foundSensor = sensorRepository.findSensorByName("Temperature Sensor");

        assertTrue(foundSensor.isPresent());
        assertEquals("Temperature Sensor", foundSensor.get().getName());
        verify(sensorRepository, times(1)).findSensorByName("Temperature Sensor");
    }

    @Test
    void findSensorByName_SensorNotFound_ReturnsEmpty() {
        when(sensorRepository.findSensorByName("Unknown Sensor")).thenReturn(Optional.empty());

        Optional<Sensor> foundSensor = sensorRepository.findSensorByName("Unknown Sensor");

        assertFalse(foundSensor.isPresent());
        verify(sensorRepository, times(1)).findSensorByName("Unknown Sensor");
    }
}