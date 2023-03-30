package com.example.services;

import com.example.models.Sensor;
import com.example.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void addNewSensor(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }
}
