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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

        submitMeasurement(measurement);
    }

    @Test
    public void submitMeasurementWithEmptyUserId() throws Exception {
        Measurement measurement = Measurement.builder()
                .userId("")
                .gasUsage(25.5)
                .coldWaterUsage(10.0)
                .hotWaterUsage(15.0)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/measurements/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(measurement)))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors[0]").value("userId: User ID cannot be blank"));
    }

    @Test
    public void getMeasurementsHistory() throws Exception {
        int userIdRecordsNum = 100;

        for (int i = 0; i < userIdRecordsNum; i++) {
            submitMeasurement(Measurement.builder()
                    .userId("testUser")
                    .gasUsage(i + 1)
                    .coldWaterUsage(i + 100)
                    .hotWaterUsage(i + 10)
                    .build());

            submitMeasurement(Measurement.builder()
                    .userId("testUser101")
                    .gasUsage(25.5)
                    .coldWaterUsage(10.0)
                    .hotWaterUsage(15.0)
                    .build());
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/measurements/history/testUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(userIdRecordsNum)));

    }

    private void submitMeasurement(Measurement measurement) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/measurements/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(measurement)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Measurement successfully created"));
    }
}
