package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Output;
import com.example.warehouseappjparelations.payload.OutputDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.OutputRepository;
import com.example.warehouseappjparelations.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;
    @Autowired
    OutputRepository outputRepository;
    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        Result result = outputService.addOutput(outputDto);
        return result;
    }
    @PutMapping("/edit/{id}")
    public Result editnput(@RequestBody OutputDto outputDto, @PathVariable  Integer id){
        Result result = outputService.editOutput(outputDto, id);
        return result;
    }
    @GetMapping("/all")
    public List<Output> getOutputList(){
        List<Output> outputList = outputRepository.findAll();
        return outputList;
    }
    @GetMapping("/{id}")
    public Object getOutputProduct(@PathVariable Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent()) return new Result("Output  with current id not found!", false);
        Output output = optionalOutput.get();
        return output;
    }
    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent()) return new Result("Output product with current id not found!", false);
        outputRepository.delete(optionalOutput.get());
        return new Result("Chosen output product deleted successfully!", true);
    }
}
