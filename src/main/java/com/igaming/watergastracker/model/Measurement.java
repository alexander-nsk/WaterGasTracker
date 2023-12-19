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

    public static class Builder {
        private String userId;
        private double gasUsage;
        private double coldWaterUsage;
        private double hotWaterUsage;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder gasUsage(double gasUsage) {
            this.gasUsage = gasUsage;
            return this;
        }

        public Builder coldWaterUsage(double coldWaterUsage) {
            this.coldWaterUsage = coldWaterUsage;
            return this;
        }

        public Builder hotWaterUsage(double hotWaterUsage) {
            this.hotWaterUsage = hotWaterUsage;
            return this;
        }

        public Measurement build() {
            Measurement measurement = new Measurement();
            measurement.userId = this.userId;
            measurement.gasUsage = this.gasUsage;
            measurement.coldWaterUsage = this.coldWaterUsage;
            measurement.hotWaterUsage = this.hotWaterUsage;
            return measurement;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}