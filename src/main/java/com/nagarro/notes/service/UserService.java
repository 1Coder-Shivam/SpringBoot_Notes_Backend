package com.nagarro.notes.service;

import com.nagarro.notes.dto.UserDto;
import com.nagarro.notes.entity.User;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto user);

    UserDto fetchUserByEmailId(String emailId);



    UserDto registerUser(UserDto user) throws Exception;
    List<UserDto> getAllUsers();


}
