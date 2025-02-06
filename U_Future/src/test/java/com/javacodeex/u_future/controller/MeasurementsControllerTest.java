package com.javacodeex.u_future.controller;

import com.javacodeex.u_future.domain.dto.MeasurementDto;
import com.javacodeex.u_future.domain.dto.response.RainyDaysCountDTO;
import com.javacodeex.u_future.service.MeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MeasurementsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementsController measurementsController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(measurementsController).build();
    }

    @Test
    void getMeasurements_ReturnsList() throws Exception {
        when(measurementService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/measurements")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(measurementService, times(1)).findAll();
    }

    @Test
    void showRainyDays_ReturnsRainyDaysCount() throws Exception {
        RainyDaysCountDTO rainyDaysCountDTO = RainyDaysCountDTO.builder()
                .rainyDaysCount(5L)
                .build();
        when(measurementService.getRainingMeasurementsCount()).thenReturn(rainyDaysCountDTO);

        mockMvc.perform(get("/measurements/rainyDaysCount")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rainyDaysCount").value(5));

        verify(measurementService, times(1)).getRainingMeasurementsCount();
    }

    @Test
    void addMeasurement_ValidInput_ReturnsOk() throws Exception {
        MeasurementDto measurementDto = new MeasurementDto();
        doNothing().when(measurementService).addMeasurement(any(), any());

        mockMvc.perform(post("/measurements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"temperature\":25,\"raining\":true,\"sensorId\":1}"))
                .andExpect(status().isOk());

        verify(measurementService, times(1)).addMeasurement(any(), any());
    }
}
