package com.igaming.watergastracker.service;

import com.igaming.watergastracker.model.Measurement;

import java.util.List;

public interface MeasurementService {
    void submitMeasurement(Measurement measurement);
    List<Measurement> getMeasurementHistory(String userId);
}
