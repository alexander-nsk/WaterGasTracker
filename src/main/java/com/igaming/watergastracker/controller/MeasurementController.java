package com.igaming.watergastracker.controller;

import com.igaming.watergastracker.model.Measurement;
import com.igaming.watergastracker.service.MeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Operation(summary = "Submit a New Measurement")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Measurement successfully created")})
    @PostMapping("/submit")
    public ResponseEntity<String> submitMeasurement(@RequestBody Measurement measurement) {
        measurementService.submitMeasurement(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).body("Measurement successfully created");
    }

    @Operation(summary = "Get Measurement History")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Measurements history retrieved")})
    @GetMapping("/history/{userId}")
    public List<Measurement> getMeasurementHistory(@PathVariable String userId) {
        return measurementService.getMeasurementHistory(userId);
    }
}