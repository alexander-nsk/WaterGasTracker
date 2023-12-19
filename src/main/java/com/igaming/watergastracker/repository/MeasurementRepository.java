package com.igaming.watergastracker.repository;

import com.igaming.watergastracker.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findByUserId(String userId);
}
