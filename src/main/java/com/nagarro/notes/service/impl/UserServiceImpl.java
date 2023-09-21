package com.nagarro.notes.service.impl;

import com.nagarro.notes.custom.exception.UserAlreadyExistsException;
import com.nagarro.notes.dto.UserDto;
import com.nagarro.notes.entity.User;
import com.nagarro.notes.mapper.UserMapper;
import com.nagarro.notes.repository.RegistrationRepository;
import com.nagarro.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RegistrationRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserMapper mapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = mapper.dtoToUser(userDto);
        return mapper.userToDto(repo.save(user));
    }

    @Override
    public UserDto fetchUserByEmailId(String emailId) {
        User user = repo.findByEmailId(emailId);
        return user != null ? mapper.userToDto(user) : null;
    }


    @Override
    public UserDto registerUser(UserDto userDto) throws Exception {
        String tempEmailId = userDto.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            UserDto userObj = fetchUserByEmailId(tempEmailId);
            if (userObj != null) {
                throw new UserAlreadyExistsException("User with emailId " + tempEmailId + " already exists.");
            }
        }
        String password = userDto.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        userDto.setPassword(hashedPassword);
        UserDto savedUser = saveUser(userDto);
        return savedUser;
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = repo.findAll();
        return users.stream()
                .map(mapper::userToDto)
                .collect(Collectors.toList());
    }





}