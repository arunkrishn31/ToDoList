package com.project.todolist.service;

import com.project.todolist.entity.User;
import com.project.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserRepository userInforepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(User userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInforepository.save(userInfo);
        return "User Added Successfully";
    }
}
