package com.igaming.watergastracker.model;


import javax.persistence.*;

@Entity
@Table(name = "measurement_table",
        indexes = {
                @Index(name = "idx_userId", columnList = "userId")
        })
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