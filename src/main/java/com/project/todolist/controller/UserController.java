package com.project.todolist.controller;

import com.project.todolist.dto.UserDto;
import com.project.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>>getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUserbyId(@PathVariable("id") int id){
        UserDto userbyId = userService.getUserbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(userbyId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUserbyId(@PathVariable("id") int id, @RequestBody UserDto userDto){
        UserDto updateUser= userService.updateUserById(id,userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>DeleteUserbyId(@PathVariable("id") int id){
         userService.deleteUserById(id);
        return new  ResponseEntity<>("Successfully deleted",HttpStatus.NO_CONTENT);
    }
}
