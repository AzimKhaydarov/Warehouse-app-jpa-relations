package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Warehouse;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.WarehouseRepository;
import com.example.warehouseappjparelations.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    WarehouseRepository warehouseRepository;

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {
        Result result = warehouseService.addWarehouseService(warehouse);
        return result;
    }

    @GetMapping("/all")
    public List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = warehouseService.getWarehouses();
        return warehouses;
    }

    @GetMapping("/{id}")
    public Object getWarehouse(@PathVariable Integer id) {
        Object result = warehouseService.getWarehouse(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editWarehouse(@RequestBody Warehouse warehouse, @PathVariable Integer id) {
        Result result = warehouseService.editWarehouseService(warehouse, id);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id) {
        Result result = warehouseService.deleteWarehouse(id);
        return result;
    }
}
