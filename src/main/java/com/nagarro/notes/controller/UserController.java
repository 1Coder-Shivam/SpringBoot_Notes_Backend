package com.nagarro.notes.controller;

import com.nagarro.notes.dto.UserDto;
import com.nagarro.notes.mapper.UserMapper;
import com.nagarro.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/users")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto user) throws Exception {
        UserDto savedUser =userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }



}
