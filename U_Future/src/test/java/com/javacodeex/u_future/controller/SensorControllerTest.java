package com.javacodeex.u_future.controller;

import com.javacodeex.u_future.domain.dto.SensorDTO;
import com.javacodeex.u_future.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SensorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SensorService sensorService;

    @InjectMocks
    private SensorController sensorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sensorController).build();
    }

    @Test
    void create_ValidSensor_ReturnsOk() throws Exception {
        SensorDTO sensorDTO = new SensorDTO();
        doNothing().when(sensorService).create(any(), any());

        mockMvc.perform(post("/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Sensor\"}"))
                .andExpect(status().isOk());

        verify(sensorService, times(1)).create(any(), any());
    }
}
