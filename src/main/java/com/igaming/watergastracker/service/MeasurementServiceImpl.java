package com.igaming.watergastracker.service;

import com.igaming.watergastracker.model.Measurement;
import com.igaming.watergastracker.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void submitMeasurement(Measurement measurement) {
        if (measurement.getUserId() == null || measurement.getGasUsage() < 0 ||
                measurement.getColdWaterUsage() < 0 || measurement.getHotWaterUsage() < 0) {
            throw new IllegalArgumentException("Invalid measurement data");
        }

        measurementRepository.save(measurement);
    }

    @Override
    public List<Measurement> getMeasurementHistory(String userId) {
        return measurementRepository.findByUserId(userId);
    }
}
