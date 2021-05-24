package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Supplier;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier) {
        boolean exists = supplierRepository.existsByNameAndPhoneNumber(supplier.getName(), supplier.getPhoneNumber());
        if (exists) return new Result("Supplier already exists!", false);
        supplier.setName(supplier.getName());
        supplier.setPhoneNumber(supplier.getPhoneNumber());
        supplier.setActive(supplier.isActive());
        Supplier saved = supplierRepository.save(supplier);
        return new Result("Supplier added successfully!", true);
    }

    public Result editSupplier(Supplier supplier, Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        Supplier supplier1 = optionalSupplier.get();
        supplier1.setName(supplier.getName());
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if(existsByPhoneNumber) return new Result("Current phone number already registered!", false);
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplier1.setActive(supplier.isActive());
        supplierRepository.save(supplier1);
        return new Result("Supplier edited successfully!", true);

    }
    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }
    public Object getSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(!optionalSupplier.isPresent()) return new Result("The supplier with current id not found", false);
        return optionalSupplier.get();}

    public Result deleteSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) return new Result("The supplier not found!", false);
        Supplier supplier1 = optionalSupplier.get();
        supplierRepository.delete(supplier1);
        return new Result("The supplier deleted successfully!", true);
    }
}
