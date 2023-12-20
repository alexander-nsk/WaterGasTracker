package com.igaming.watergastracker.model;


import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "measurement_table",
        indexes = {
                @Index(name = "idx_userId", columnList = "userId")
        })
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "User ID cannot be blank")
    private String userId;
    @Required
    @NotNull(message = "Gas usage is required")
    private Double gasUsage;
    @NotNull(message = "Cold water usage is required")
    @Positive(message = "Cold water usage must be a positive value")
    private Double coldWaterUsage;
    @NotNull(message = "Hot water usage is required")
    @Positive(message = "Hot water usage must be a positive value")
    private Double hotWaterUsage;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Double.compare(gasUsage, that.gasUsage) == 0 && Double.compare(coldWaterUsage, that.coldWaterUsage) == 0 && Double.compare(hotWaterUsage, that.hotWaterUsage) == 0 && Objects.equals(id, that.id) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, gasUsage, coldWaterUsage, hotWaterUsage);
    }
}