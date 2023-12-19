package com.igaming.watergastracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igaming.watergastracker.model.Measurement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MeasurementsIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitMeasurement() throws Exception {
        Measurement measurement = Measurement.builder()
                .userId("123")
                .gasUsage(25.5)
                .coldWaterUsage(10.0)
                .hotWaterUsage(15.0)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/measurements/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(measurement)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Measurement successfully created"));
    }
}
