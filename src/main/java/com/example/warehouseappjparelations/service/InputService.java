package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Currency;
import com.example.warehouseappjparelations.entity.Input;
import com.example.warehouseappjparelations.entity.Supplier;
import com.example.warehouseappjparelations.entity.Warehouse;
import com.example.warehouseappjparelations.payload.InputDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.CurrencyRepository;
import com.example.warehouseappjparelations.repository.InputRepository;
import com.example.warehouseappjparelations.repository.SupplierRepository;
import com.example.warehouseappjparelations.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    InputRepository inputRepository;
    public Result addInput(InputDto inputDto){
        Input input = new Input();
        input.setDate((Timestamp) inputDto.getDate());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent()) return new Result("Chosen warehouse doesn't exist yet!", false);
        input.setWarehouse(optionalWarehouse.get());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if(!optionalSupplier.isPresent()) return new Result("Chosen supplier doesn't exist yet!", false);
        input.setSupplier(optionalSupplier.get());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalCurrency.isPresent()) return new Result("Chosen currency unit doesn't exist yet!", false);
        input.setCurrency(optionalCurrency.get());
        input.setInvoiceNumber(inputDto.getInvoiceNumber());
        String uuid = String.valueOf(UUID.randomUUID());
        input.setCode(uuid);
        return new Result("The input added successfully!", true);
    }
    public Result editInput(InputDto inputDto, Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent()) return new Result("Chosen input doesn't exist yet!", false);
        Input input = optionalInput.get();
        input.setDate((Timestamp) inputDto.getDate());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent()) return new Result("Chosen warehouse doesn't exist yet!", false);
        input.setWarehouse(optionalWarehouse.get());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if(!optionalSupplier.isPresent()) return new Result("Chosen supplier doesn't exist yet!", false);
        input.setSupplier(optionalSupplier.get());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalCurrency.isPresent()) return new Result("Chosen currency unit doesn't exist yet!", false);
        input.setCurrency(optionalCurrency.get());
        input.setInvoiceNumber(inputDto.getInvoiceNumber());
        String uuid = String.valueOf(UUID.randomUUID());
        input.setCode(uuid);
        return new Result("The input edited successfully!", true);
    }
}
