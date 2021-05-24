package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Output;
import com.example.warehouseappjparelations.entity.OutputProduct;
import com.example.warehouseappjparelations.entity.Product;
import com.example.warehouseappjparelations.payload.OutputProductDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.OutputProductRepository;
import com.example.warehouseappjparelations.repository.OutputRepository;
import com.example.warehouseappjparelations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputProductRepository outputProductRepository;
    public Result addProductOutput(OutputProductDto outputProductDto){
        OutputProduct outputProduct = new OutputProduct();
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if(!optionalProduct.isPresent()) return new Result("The product with current id does not exist!", false);
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if(!optionalOutput.isPresent()) return new Result("The output with current id does not exist!", false);
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output product added successfully!", true);
    }
    public Result editProductOutput(OutputProductDto outputProductDto, Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(!optionalOutputProduct.isPresent()) return new Result("Output product does not exist!", false);
        OutputProduct outputProduct = optionalOutputProduct.get();
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if(!optionalProduct.isPresent()) return new Result("The product with current id does not exist!", false);
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if(!optionalOutput.isPresent()) return new Result("The output with current id does not exist!", false);
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Output product edited successfully!", true);
    }
}
