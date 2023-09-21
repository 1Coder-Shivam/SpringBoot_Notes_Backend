package com.nagarro.notes.mapper;

import com.nagarro.notes.dto.UserDto;
import com.nagarro.notes.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToUser(UserDto userDto);

    UserDto userToDto(User user);
}
