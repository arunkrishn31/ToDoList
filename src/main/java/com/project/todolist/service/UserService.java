package com.project.todolist.service;

import com.project.todolist.dto.UserDto;
import com.project.todolist.entity.User;
import com.project.todolist.exception.ResourceNotFoundException;
import com.project.todolist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User save = userRepo.save(user);
        return modelMapper.map(save,UserDto.class);
  }

    public List<UserDto> getAllUsers() {
        return  userRepo.findAll()
                .stream()
                .map(ele->modelMapper.map(ele, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto getUserbyId(int id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("USer","id",+id));
        return modelMapper.map(user,UserDto.class);
    }

    public UserDto updateUserById(int id , UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    public void deleteUserById(int id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("USer","id",+id));
                    userRepo.deleteById(id);
    }
}
