package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Currency;
import com.example.warehouseappjparelations.entity.Output;
import com.example.warehouseappjparelations.entity.Client;
import com.example.warehouseappjparelations.entity.Warehouse;
import com.example.warehouseappjparelations.payload.OutputDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.CurrencyRepository;
import com.example.warehouseappjparelations.repository.OutputRepository;
import com.example.warehouseappjparelations.repository.ClientRepository;
import com.example.warehouseappjparelations.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OutputRepository outputRepository;
    public Result addOutput(OutputDto outputDto){
        Output output = new Output();
        output.setDate((Timestamp) outputDto.getDate());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent()) return new Result("Chosen warehouse doesn't exist yet!", false);
        output.setWarehouse(optionalWarehouse.get());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(!optionalClient.isPresent()) return new Result("Chosen client doesn't exist yet!", false);
        output.setClient(optionalClient.get());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(!optionalCurrency.isPresent()) return new Result("Chosen currency unit doesn't exist yet!", false);
        output.setCurrency(optionalCurrency.get());
        output.setInvoiceNumber(outputDto.getInvoiceNumber());
        String uuid = String.valueOf(UUID.randomUUID());
        output.setCode(uuid);
        return new Result("The output added successfully!", true);
    }
    public Result editOutput(OutputDto outputDto, Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent()) return new Result("Chosen output doesn't exist yet!", false);
        Output output = optionalOutput.get();
        output.setDate((Timestamp) outputDto.getDate());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent()) return new Result("Chosen warehouse doesn't exist yet!", false);
        output.setWarehouse(optionalWarehouse.get());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(!optionalClient.isPresent()) return new Result("Chosen client doesn't exist yet!", false);
        output.setClient(optionalClient.get());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(!optionalCurrency.isPresent()) return new Result("Chosen currency unit doesn't exist yet!", false);
        output.setCurrency(optionalCurrency.get());
        output.setInvoiceNumber(outputDto.getInvoiceNumber());
        String uuid = String.valueOf(UUID.randomUUID());
        output.setCode(uuid);
        return new Result("The output edited successfully!", true);
    }
}
