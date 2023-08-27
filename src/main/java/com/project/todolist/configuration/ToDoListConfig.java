package com.project.todolist.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoListConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
