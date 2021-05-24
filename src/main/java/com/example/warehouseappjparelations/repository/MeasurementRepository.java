package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository <Measurement, Integer>{
boolean existsByName(String name);
}
