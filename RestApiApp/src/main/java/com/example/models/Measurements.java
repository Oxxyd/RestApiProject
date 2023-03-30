package com.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "Measurements")
public class Measurements {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Max(value = 100, message = "value should be no bigger than 100")
    @Min(value = -100, message = "value should be no less than -100 ")
    private float value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "date")
    private Date date;

    @Transient
    @NotEmpty(message = "sensorName field should not be empty")
    private String sensorName;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor owner;

    public Measurements(){}

    public Measurements(int id, float value, boolean raining) {
        this.id = id;
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getOwner() {
        return owner;
    }

    public void setOwner(Sensor owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Measurement: " +
                "value=" + value +
                ", raining=" + raining +
                ", owner=" + owner;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
