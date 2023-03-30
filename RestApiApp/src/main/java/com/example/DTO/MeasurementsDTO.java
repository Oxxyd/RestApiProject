package com.example.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;

public class MeasurementsDTO {
    @Max(value = 100, message = "value should be no bigger than 100")
    @Min(value = -100, message = "value should be no less than -100 ")
    private float value;

    private boolean raining;

    @NotEmpty(message = "sensorName field should not be empty")
    private String sensorName;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
