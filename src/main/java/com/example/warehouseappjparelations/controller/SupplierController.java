package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Supplier;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.SupplierRepository;
import com.example.warehouseappjparelations.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    SupplierRepository supplierRepository;

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        Result result = supplierService.addSupplier(supplier);
        return result;
    }

    @GetMapping("/all")
    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = supplierService.getSuppliers();
        return suppliers;
    }

    @GetMapping("/{id}")
    public Object getSupplier(@PathVariable Integer id) {
        Object result = supplierService.getSupplier(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editSupplier(@RequestBody Supplier supplier, @PathVariable Integer id) {
        Result result = supplierService.editSupplier(supplier, id);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        Result result = supplierService.deleteSupplier(id);
        return result;
    }
}
