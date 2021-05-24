package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository <Currency, Integer>{
boolean existsByName(String name);
}
