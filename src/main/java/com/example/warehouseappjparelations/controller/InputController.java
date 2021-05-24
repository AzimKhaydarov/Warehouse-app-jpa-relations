package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Input;
import com.example.warehouseappjparelations.entity.InputProduct;
import com.example.warehouseappjparelations.payload.InputDto;
import com.example.warehouseappjparelations.payload.InputProductDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.InputRepository;
import com.example.warehouseappjparelations.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;
    @Autowired
    InputRepository inputRepository;
    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        Result result = inputService.addInput(inputDto);
        return result;
    }
    @PutMapping("/edit/{id}")
    public Result editnput(@RequestBody InputDto inputDto, @PathVariable  Integer id){
        Result result = inputService.editInput(inputDto, id);
        return result;
    }
    @GetMapping("/all")
    public List<Input> getInputList(){
        List<Input> inputList = inputRepository.findAll();
        return inputList;
    }
    @GetMapping("/{id}")
    public Object getInputProduct(@PathVariable Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent()) return new Result("Input  with current id not found!", false);
        Input input = optionalInput.get();
        return input;
    }
    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent()) return new Result("Input product with current id not found!", false);
        inputRepository.delete(optionalInput.get());
        return new Result("Chosen input product deleted successfully!", true);
    }
}
