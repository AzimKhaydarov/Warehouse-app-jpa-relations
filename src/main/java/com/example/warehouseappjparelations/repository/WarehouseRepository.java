package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository <Warehouse, Integer>{
boolean existsByName(String name);
}
