package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Warehouse;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouseService(Warehouse warehouse) {
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName)
            return new Result("Warehouse already exists!", false);
        Warehouse saved = warehouseRepository.save(warehouse);
        return new Result("Warehouse  added successfully!", true);
    }

    public Result editWarehouseService(Warehouse warehouse, Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        Warehouse warehouse1 = optionalWarehouse.get();
        if (!optionalWarehouse.isPresent()) return new Result("The warehouse not found!", false);
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());
        warehouseRepository.save(warehouse1);
        return new Result("Warehouse edited successfully!", true);

    }
    public List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses;
    }
    public Object getWarehouse(@PathVariable Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(!optionalWarehouse.isPresent()) return new Result("The warehouse with current id not found", false);
        return optionalWarehouse.get();}

    public Result deleteWarehouse(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()) return new Result("The warehouse not found!", false);
        Warehouse warehouse1 = optionalWarehouse.get();
        warehouseRepository.delete(warehouse1);
        return new Result("The warehouse deleted successfully!", true);
    }
}
