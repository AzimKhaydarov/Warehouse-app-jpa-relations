package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.User;
import com.example.warehouseappjparelations.entity.Warehouse;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.payload.UserDto;
import com.example.warehouseappjparelations.repository.UserRepository;
import com.example.warehouseappjparelations.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(UserDto userDto) {
        User user1 = new User();
        boolean exists = userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDto.getFirstName(), userDto.getLastName(), userDto.getPhoneNumber());
        if (exists) return new Result("The user already exists in user list!", false);
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) return new Result("The phone number already registered!", false);
        user1.setPhoneNumber(userDto.getPhoneNumber());
        String uuid = String.valueOf(UUID.randomUUID());
        user1.setCode(uuid);
        user1.setPassword(userDto.getPassword());
        user1.setActive(userDto.isActive());

        Set<Integer> warehouses = userDto.getWarehouseList();
        Set<Warehouse> existedWarehouses = new HashSet<>();
        String notExistedOnes = "";
        for (Integer warehouse : warehouses) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse);
            if (!optionalWarehouse.isPresent()) {
                notExistedOnes += warehouse + ",";
            } else {
                existedWarehouses.add(optionalWarehouse.get());
            }
        }
        if(existedWarehouses.isEmpty()) return new Result("The following warehouses:" +notExistedOnes+" not found", false);
        user1.setWarehouses(existedWarehouses);
        userRepository.save(user1);
        return new Result("The user added successfully!", true);
    }

    public Result editUser(UserDto userDto, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new Result("The user not found!", false);
        User user1 = optionalUser.get();
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) return new Result("The phone number already registered!", false);
        user1.setPhoneNumber(userDto.getPhoneNumber());
        String uuid = String.valueOf(UUID.randomUUID());
        user1.setCode(uuid);
        user1.setPassword(userDto.getPassword());
        user1.setActive(userDto.isActive());
        Set<Integer> warehouses = userDto.getWarehouseList();
        Set<Warehouse> existedWarehouses = new HashSet<>();
        String notExistedOnes = "";
        for (Integer warehouse : warehouses) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse);
            if (!optionalWarehouse.isPresent()) {
                notExistedOnes += warehouse + ",";
            } else {
                existedWarehouses.add(optionalWarehouse.get());
            }
        }
        if(existedWarehouses.isEmpty()) return new Result("The following warehouses:" +notExistedOnes+" not found", false);
        user1.setWarehouses(existedWarehouses);
        userRepository.save(user1);
        return new Result("The user edited successfully!", true);
    }
}
