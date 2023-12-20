package com.igaming.watergastracker.service;

import com.igaming.watergastracker.model.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MeasurementService {
    void submitMeasurement(Measurement measurement);

    List<Measurement> getMeasurementHistory(String userId);

    Page<Measurement> getMeasurementHistoryWithPagination(String userId, Pageable pageable);
}
