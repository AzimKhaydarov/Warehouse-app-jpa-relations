package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Attachment;
import com.example.warehouseappjparelations.entity.Category;
import com.example.warehouseappjparelations.entity.Measurement;
import com.example.warehouseappjparelations.entity.Product;
import com.example.warehouseappjparelations.payload.ProductDto;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.AttachmentRepository;
import com.example.warehouseappjparelations.repository.CategoryRepository;
import com.example.warehouseappjparelations.repository.MeasurementRepository;
import com.example.warehouseappjparelations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId());
        if(exists) return new Result("The current product already exists!", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) return new Result("Current category not found!", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if(!optionalAttachment.isPresent()) return new Result("Current attachment not found!", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if(!optionalMeasurement.isPresent()) return new Result("Current measurement not found!", false);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        String uuid = String.valueOf(UUID.randomUUID());
        product.setCode(uuid);
        product.setActive(productDto.isStatus());
        Product savedProduct = productRepository.save(product);
        return new Result("Product added successfully", true );
    }
    public Result editProduct(ProductDto productDto, Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()) return new Result ("Current product doesn't exist!", false);
        Product selectedProduct = optionalProduct.get();
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) return new Result("Current category not found!", false);
        selectedProduct.setCategory(optionalCategory.get());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if(!optionalAttachment.isPresent()) return new Result("Current attachment not found!", false);
        boolean existsByNameAndPhoto_id = productRepository.existsByNameAndPhoto_Id(productDto.getName(), productDto.getPhotoId());
        if(existsByNameAndPhoto_id) return new Result("Current product already exist in product list!", false);
        selectedProduct.setPhoto(optionalAttachment.get());
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(!optionalMeasurement.isPresent()) return new Result("Current measurement not found!", false);
        selectedProduct.setMeasurement(optionalMeasurement.get());
        String uuid = String.valueOf(UUID.randomUUID());
        selectedProduct.setCode(uuid);
        selectedProduct.setActive(productDto.isStatus());
        productRepository.save(selectedProduct);
        return new Result ("Product edited successfully!", true);
    }
    public List<Product> getProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }
    public Object getProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()) return new Result ("The chosen product not found!", false);
        return optionalProduct.get();
    }
    public Result deleteProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()) return new Result ("The chosen product not found!", false);
        productRepository.delete(optionalProduct.get());
        return new Result("The chosen product deleted successfully!", true);
    }
}
