package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.User;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.payload.UserDto;
import com.example.warehouseappjparelations.repository.UserRepository;
import com.example.warehouseappjparelations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;
@Autowired
    UserRepository userRepository;
    @PostMapping("/add")
    public Result addUser(@RequestBody UserDto userDto) {
        Result result = userService.addUser(userDto);
        return result;
    }
    @PutMapping("/{id}")
    public Result editUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        Result result = userService.editUser(userDto, id);
        return result;
    }
    @GetMapping
    public List<User> getUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }
    @GetMapping("/{id}")
    public Object getUser(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) return new Result("User with current id does not exist!", false);
        User user = optionalUser.get();
        return user;
    }
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) return new Result("User with current id does not exist!", false);
        userRepository.delete(optionalUser.get());
        return new Result("User deleted successfully!", true);
    }
}
