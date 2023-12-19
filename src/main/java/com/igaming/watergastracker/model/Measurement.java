package com.igaming.watergastracker.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;
    private double gasUsage;
    private double coldWaterUsage;
    private double hotWaterUsage;

    public String getUserId() {
        return userId;
    }

    public double getGasUsage() {
        return gasUsage;
    }

    public double getColdWaterUsage() {
        return coldWaterUsage;
    }

    public double getHotWaterUsage() {
        return hotWaterUsage;
    }
}