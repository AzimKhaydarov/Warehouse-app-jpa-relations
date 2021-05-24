package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Product;
import com.example.warehouseappjparelations.payload.ProductDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return result;
    }
    @PutMapping("/{id}")
    public Result editProduct(@RequestBody ProductDto productDto, @PathVariable Integer id){
        Result result = productService.editProduct(productDto, id);
        return result;
    }
    @GetMapping
    public List<Product> getProducts(){
        List<Product> products = productService.getProducts();
        return products;
    }
    @GetMapping("/{id}")
    public Object getProduct(@PathVariable Integer id){
        Object result = productService.getProduct(id);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        return result;
    }

}
