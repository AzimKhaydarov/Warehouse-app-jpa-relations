package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier, Integer>{
boolean existsByNameAndPhoneNumber(String name, String phone_number);
boolean existsByPhoneNumber(String phone_number);
}
