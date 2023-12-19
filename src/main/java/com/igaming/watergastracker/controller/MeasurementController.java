package com.igaming.watergastracker.controller;

import com.igaming.watergastracker.model.Measurement;
import com.igaming.watergastracker.service.MeasurementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public void submitMeasurement(@RequestBody Measurement measurement) {
        measurementService.submitMeasurement(measurement);
    }

    @GetMapping("/{userId}")
    public List<Measurement> getMeasurementHistory(@PathVariable String userId) {
        return measurementService.getMeasurementHistory(userId);
    }
}