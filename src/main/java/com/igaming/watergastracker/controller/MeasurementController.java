package com.igaming.watergastracker.controller;

import com.igaming.watergastracker.model.Measurement;
import com.igaming.watergastracker.service.MeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @CacheEvict(value = "measurements", allEntries = true)
    public ResponseEntity<Void> submitMeasurement(@Valid @RequestBody Measurement measurement) {
        measurementService.submitMeasurement(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get Measurement History")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Measurements history retrieved")})
    @GetMapping("/history/{userId}")
    public List<Measurement> getMeasurementHistory(@PathVariable String userId) {
        return measurementService.getMeasurementHistory(userId);
    }

    @Operation(summary = "Get Measurement History with Pagination")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Measurements history retrieved with pagination")})
    @GetMapping("/history/{userId}/paged")
    public ResponseEntity<Page<Measurement>> getMeasurementHistoryWithPagination(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Measurement> measurementPage = measurementService.getMeasurementHistoryWithPagination(userId, pageable);
        return ResponseEntity.ok().body(measurementPage);
    }
}