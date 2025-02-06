package com.javacodeex.u_future.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeasurementRepositoryTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @BeforeEach
    void setUp() {
        when(measurementRepository.countRainingMeasurements()).thenReturn(2L);
    }

    @Test
    void countRainingMeasurements_ReturnsCorrectCount() {
        long count = measurementRepository.countRainingMeasurements();

        assertEquals(2L, count);
        verify(measurementRepository, times(1)).countRainingMeasurements();
    }
}
