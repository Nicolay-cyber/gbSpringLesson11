package com.example.gbspringlesson11.converter;

import com.example.gbspringlesson11.dto.UserDto;
import com.example.gbspringlesson11.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User userFromDto(UserDto userDto){
        return new User(userDto.getId(),userDto.getUsername(),userDto.getEmail());
    }
    public static UserDto dtoFromUser(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

}
