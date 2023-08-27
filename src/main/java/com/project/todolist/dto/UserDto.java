package com.project.todolist.dto;

import com.project.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private List<ToDoList>toDoLists;


}
