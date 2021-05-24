package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.InputProduct;
import com.example.warehouseappjparelations.payload.InputProductDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.InputProductRepository;
import com.example.warehouseappjparelations.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product_input")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;
    @Autowired
    InputProductRepository inputProductRepository;
    @PostMapping("/add")
    public Result addProductInput(@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.addProductInput(inputProductDto);
        return result;
    }
    @PutMapping("/edit/{id}")
    public Result editProductInput(@RequestBody InputProductDto inputProductDto, @PathVariable  Integer id){
        Result result = inputProductService.editProductInput(inputProductDto, id);
        return result;
    }
    @GetMapping("/all")
    public List<InputProduct> getInputProductList(){
        List<InputProduct> inputProductList = inputProductRepository.findAll();
        return inputProductList;
    }
    @GetMapping("/{id}")
    public Object getInputProduct(@PathVariable Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(!optionalInputProduct.isPresent()) return new Result("Input product with current id not found!", false);
        InputProduct inputProduct = optionalInputProduct.get();
        return inputProduct;
    }
    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(!optionalInputProduct.isPresent()) return new Result("Input product with current id not found!", false);
        inputProductRepository.delete(optionalInputProduct.get());
        return new Result("Chosen input product deleted successfully!", true);
    }
}
