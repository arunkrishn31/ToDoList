package com.project.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Id")
		private int id;
		@Column(name="name" ,nullable = false)
		private String name;
		@Column(name = "Email",nullable = false,unique = true)
		private String email;
		@Column(name = "password",nullable = false)
		private String password;
		@Column(name = "role",nullable = false)
		private String role;
		@OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
		private List<ToDoList>toDoLists= new ArrayList<>();
}
