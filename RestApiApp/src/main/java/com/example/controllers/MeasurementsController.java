package com.example.controllers;


import com.example.DTO.MeasurementsDTO;
import com.example.models.Measurements;
import com.example.services.MeasurementsService;
import com.example.util.MeasurementNotAddedException;
import com.example.util.MeasurementsErrorResoponse;
import com.example.util.SensorNotAddedException;
import com.example.util.SensorNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService,  ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementsDTO measurementsDto,
                                                     BindingResult bindingResult) throws MeasurementNotAddedException, SensorNotFoundException {
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMessage.append(error.getField()).append("; ").
                        append(error.getDefaultMessage()).append(".");
            }
            throw new MeasurementNotAddedException(errorMessage.toString());
        }
        measurementsService.add(convertToMeasurements(measurementsDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<String> getMeasurements(){
        return measurementsService.getMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount(){
        long quantity = measurementsService.getRainyDays();

        return "Quantity of raining days is " + quantity;
    }

    public Measurements convertToMeasurements(MeasurementsDTO measurementsDTO){
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResoponse> handleException(SensorNotFoundException e){
        MeasurementsErrorResoponse response = new MeasurementsErrorResoponse(
                "Sensor with this name wasn't found!",
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResoponse> handleException(MeasurementNotAddedException e){
        MeasurementsErrorResoponse response = new MeasurementsErrorResoponse(
                                            e.getMessage(),
                                            System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
