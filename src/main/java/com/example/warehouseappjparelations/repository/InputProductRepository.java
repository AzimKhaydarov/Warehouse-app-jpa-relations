package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Input;
import com.example.warehouseappjparelations.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

}
