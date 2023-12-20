package com.igaming.watergastracker.model;


import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
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
    private BigDecimal gasUsage;
    @NotNull(message = "Cold water usage is required")
    @Positive(message = "Cold water usage must be a positive value")
    private BigDecimal coldWaterUsage;
    @NotNull(message = "Hot water usage is required")
    @Positive(message = "Hot water usage must be a positive value")
    private BigDecimal hotWaterUsage;

    public String getUserId() {
        return userId;
    }

    public BigDecimal getGasUsage() {
        return gasUsage;
    }

    public BigDecimal getColdWaterUsage() {
        return coldWaterUsage;
    }

    public BigDecimal getHotWaterUsage() {
        return hotWaterUsage;
    }

    public static class Builder {
        private String userId;
        private BigDecimal gasUsage;
        private BigDecimal coldWaterUsage;
        private BigDecimal hotWaterUsage;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder gasUsage(BigDecimal gasUsage) {
            this.gasUsage = gasUsage;
            return this;
        }

        public Builder coldWaterUsage(BigDecimal coldWaterUsage) {
            this.coldWaterUsage = coldWaterUsage;
            return this;
        }

        public Builder hotWaterUsage(BigDecimal hotWaterUsage) {
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
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(gasUsage, that.gasUsage) && Objects.equals(coldWaterUsage, that.coldWaterUsage) && Objects.equals(hotWaterUsage, that.hotWaterUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, gasUsage, coldWaterUsage, hotWaterUsage);
    }
}