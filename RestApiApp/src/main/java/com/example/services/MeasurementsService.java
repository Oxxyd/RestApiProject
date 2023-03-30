package com.example.services;

import com.example.models.Measurements;
import com.example.models.Sensor;
import com.example.repositories.MeasurementsRepository;
import com.example.util.SensorNotFoundException;
import com.google.gson.Gson;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void add(Measurements measurements) throws SensorNotFoundException {
        Sensor sensor = sensorService.findByName(measurements.getSensorName());
        if(sensor == null)
            throw new SensorNotFoundException();
        Date date = new Date();
        measurements.setOwner(sensor);
        measurements.setDate(date);

        measurementsRepository.save(measurements);
    }

    public List<String> getMeasurements() {
        List<String> response = new ArrayList<>();
        List<Measurements> list = measurementsRepository.findAll();
        for(Measurements m: list){
            response.add(m.toString());
        }

        return response;
    }

    public long getRainyDays(){
        return measurementsRepository.countByRainingTrue();
    }

}
