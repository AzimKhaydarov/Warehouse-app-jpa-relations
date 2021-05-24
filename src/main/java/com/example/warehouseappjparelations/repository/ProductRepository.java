package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameAndCategory_Id(String name, Integer category_id);
boolean existsByNameAndPhoto_Id(String name, Integer photo_id);
}
