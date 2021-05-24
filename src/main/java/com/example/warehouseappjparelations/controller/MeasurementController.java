package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Measurement;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.MeasurementRepository;
import com.example.warehouseappjparelations.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;
    @Autowired
    MeasurementRepository measurementRepository;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement) {
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @GetMapping("/all")
    public List<Measurement> getMeasurements() {
        List<Measurement> measurements = measurementService.getMeasurements();
        return measurements;
    }

    @GetMapping("/{id}")
    public Object getMeasurement(@PathVariable Integer id) {
        Object result = measurementService.getMeasurement(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@RequestBody Measurement measurement, @PathVariable Integer id) {
        Result result = measurementService.editMeasurementService(measurement, id);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id) {
        Result result = measurementService.deleteMeasurement(id);
        return result;
    }
}
