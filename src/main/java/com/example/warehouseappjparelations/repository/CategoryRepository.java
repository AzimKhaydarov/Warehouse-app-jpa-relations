package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
