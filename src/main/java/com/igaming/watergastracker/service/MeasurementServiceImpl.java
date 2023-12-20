package com.igaming.watergastracker.service;

import com.igaming.watergastracker.model.Measurement;
import com.igaming.watergastracker.repository.MeasurementRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    /**
     * Submits a new measurement for monitoring gas, cold, and hot water usage.
     *
     * @param measurement The Measurement object representing the measurement data.
     * @throws IllegalArgumentException If the measurement data is invalid.
     */
    @Override
    @Transactional
    @CacheEvict(value = "measurements", key = "#measurement.userId")
    public void submitMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    /**
     * Retrieves the measurement history for a given user ID.
     * It uses test cache manager. Redis or coffeine should be used for production.
     *
     * @param userId The user ID for which to retrieve the measurement history.
     * @return A list of Measurement objects representing the measurement history.
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "measurements", key = "#userId")
    public List<Measurement> getMeasurementHistory(String userId) {
        return measurementRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Measurement> getMeasurementHistoryWithPagination(String userId, Pageable pageable) {
        return measurementRepository.findByUserId(userId, pageable);
    }
}
