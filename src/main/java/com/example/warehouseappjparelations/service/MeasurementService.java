package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Measurement;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("Measurement unit already exists!", false);
        Measurement saved = measurementRepository.save(measurement);
        return new Result("Measurement unit added successfully!", true);
    }

    public Result editMeasurementService(Measurement measurement, Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        Measurement measurement1 = optionalMeasurement.get();
        if (!optionalMeasurement.isPresent()) return new Result("The measurement unit not found!", false);
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new Result("Measurement edited successfully!", true);

    }
    public List<Measurement> getMeasurements() {
        List<Measurement> measurements = measurementRepository.findAll();
        return measurements;
    }
    public Object getMeasurement(@PathVariable Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(!optionalMeasurement.isPresent()) return new Result("The measurement unit with current id not found", false);
        return optionalMeasurement.get();}

    public Result deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) return new Result("The measurement unit not found!", false);
        Measurement measurement1 = optionalMeasurement.get();
        measurementRepository.delete(measurement1);
        return new Result("The measurement deleted successfully!", true);
    }
}
