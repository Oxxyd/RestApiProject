package com.example.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 3, max = 30, message = "Name should be no longer than 20 characters, and no shorter than 3")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
