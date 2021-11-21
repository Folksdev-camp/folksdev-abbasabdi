package com.folksdev.blog.service;


import com.folksdev.blog.entity.User;
import com.folksdev.blog.exception.UserNotFoundException;
import com.folksdev.blog.model.converter.UserDtoConverter;
import com.folksdev.blog.model.dto.UserDto;
import com.folksdev.blog.model.request.CreateUserRequest;
import com.folksdev.blog.model.request.UpdateUserRequest;
import com.folksdev.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user ->userDtoConverter.convert(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(String id){
        return userDtoConverter.convert(findUserById(id));
    }

    public UserDto createUser(CreateUserRequest userCreateRequest){
        User user = new User(userCreateRequest.getUsername(),userCreateRequest.getFirstName(),userCreateRequest.getLastName(),userCreateRequest.getEmail());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(UpdateUserRequest updateUserRequest, String id){

        User foundUser = findUserById(id);

        User updatedUser = new User(
                foundUser.getId(),
                updateUserRequest.getUsername(),
                updateUserRequest.getFirstName(),
                updateUserRequest.getLastName(),
                updateUserRequest.getEmail(),
                foundUser.getPosts(),
                foundUser.getComments(),
                foundUser.getCreatedDate(),
                foundUser.getUpdatedDate()
        );

        return userDtoConverter.convert(userRepository.save(updatedUser));

    }

    public String deleteUser(String id){
        User user = findUserById(id);
        userRepository.delete(user);
        return id+" is deleted";
    }


    public User findUserById(String id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user not found with this id :"+id));
    }


}
