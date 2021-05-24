package com.example.warehouseappjparelations.repository;

import com.example.warehouseappjparelations.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Integer>{
boolean existsByName(String name);
}
