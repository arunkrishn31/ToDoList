package com.project.todolist.service;

import com.project.todolist.entity.User;
import com.project.todolist.entity.UserInfoUserDetails;
import com.project.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userInforepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User>userInfo =userInforepository.findByName(username);
        return userInfo.map(UserInfoUserDetails:: new).orElseThrow(()-> new UsernameNotFoundException("user is not found with name of :"+username));
    }
}
