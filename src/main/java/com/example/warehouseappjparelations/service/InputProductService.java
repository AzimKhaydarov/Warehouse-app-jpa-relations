package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Input;
import com.example.warehouseappjparelations.entity.InputProduct;
import com.example.warehouseappjparelations.entity.Product;
import com.example.warehouseappjparelations.payload.InputProductDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.InputProductRepository;
import com.example.warehouseappjparelations.repository.InputRepository;
import com.example.warehouseappjparelations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository inputProductRepository;
    public Result addProductInput(InputProductDto inputProductDto){
        InputProduct inputProduct = new InputProduct();
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if(!optionalProduct.isPresent()) return new Result("The product with current id does not exist!", false);
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate((Date) inputProductDto.getExpireDate());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if(!optionalInput.isPresent()) return new Result("The input with current id does not exist!", false);
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Input product added successfully!", true);
    }
    public Result editProductInput(InputProductDto inputProductDto, Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(!optionalInputProduct.isPresent()) return new Result("Input product does not exist!", false);
        InputProduct inputProduct = optionalInputProduct.get();
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if(!optionalProduct.isPresent()) return new Result("The product with current id does not exist!", false);
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate((Date) inputProductDto.getExpireDate());
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if(!optionalInput.isPresent()) return new Result("The input with current id does not exist!", false);
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Input product edited successfully!", true);
    }
}
