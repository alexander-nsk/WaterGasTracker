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

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MeasurementsIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String USER_ID_1 = "testUserId1";
    private final String USER_ID_2 = "testUserId2";
    private final String USER_ID_3 = "testUserId3";
    private final String USER_ID_EMPTY = "";
    private final int MEASUREMENTS_SIZE = 20;

    @Test
    void submitMeasurement() throws Exception {
        Measurement measurement = Measurement.builder()
                .userId(USER_ID_1)
                .gasUsage(BigDecimal.valueOf(25.5))
                .coldWaterUsage(BigDecimal.valueOf(10.0))
                .hotWaterUsage(BigDecimal.valueOf(15.0))
                .build();

        submitMeasurement(measurement);
    }

    @Test
    void submitMeasurementWithEmptyUserId() throws Exception {
        Measurement measurement = Measurement.builder()
                .userId(USER_ID_EMPTY)
                .gasUsage(BigDecimal.valueOf(25.5))
                .coldWaterUsage(BigDecimal.valueOf(10.0))
                .hotWaterUsage(BigDecimal.valueOf(15.0))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/measurements/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measurement)))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors[0]").value("userId: User ID cannot be blank"));
    }

    @Test
    void getMeasurementsHistory() throws Exception {
        for (int i = 0; i < MEASUREMENTS_SIZE; i++) {
            submitMeasurement(Measurement.builder()
                    .userId(USER_ID_1)
                    .gasUsage(BigDecimal.valueOf(i + 1))
                    .coldWaterUsage(BigDecimal.valueOf(i + 100))
                    .hotWaterUsage(BigDecimal.valueOf(i + 10))
                    .build());

            submitMeasurement(Measurement.builder()
                    .userId(USER_ID_2)
                    .gasUsage(BigDecimal.valueOf(25.5))
                    .coldWaterUsage(BigDecimal.valueOf(10.0))
                    .hotWaterUsage(BigDecimal.valueOf(15.0))
                    .build());
        }

        mockMvc.perform(get("/measurements/history/{userId}", USER_ID_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(MEASUREMENTS_SIZE)));
    }


    @Test
    void getMeasurementHistoryWithPagination() throws Exception {
        for (int i = 0; i < MEASUREMENTS_SIZE; i++) {
            submitMeasurement(Measurement.builder()
                    .userId(USER_ID_3)
                    .gasUsage(BigDecimal.valueOf(i + 1))
                    .coldWaterUsage(BigDecimal.valueOf(i + 100))
                    .hotWaterUsage(BigDecimal.valueOf(i + 10))
                    .build());
        }
        // Arrange
        int page = 0;
        int size = 10;

        mockMvc.perform(get("/measurements/history/{userId}/paged", USER_ID_3)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(size)) // Check the expected number of items per page
                .andExpect(jsonPath("$.totalElements").value(MEASUREMENTS_SIZE)); // Check the total number of elements
    }

    private void submitMeasurement(Measurement measurement) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/measurements/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(measurement)))
                .andExpect(status().isCreated());
    }
}
