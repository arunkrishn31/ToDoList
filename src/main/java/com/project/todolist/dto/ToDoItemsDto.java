package com.project.todolist.dto;

import java.util.Date;

import com.project.todolist.entity.ToDoList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItemsDto {
	private int id;
	private String title;
	private String description;
	private String completed;
	private Date dueDate;
	private ToDoList toDoList;
}
