package com.folksdev.blog.model.converter;


import com.folksdev.blog.entity.User;
import com.folksdev.blog.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {

    public User convert(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }

}
