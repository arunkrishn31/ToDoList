package com.project.todolist.dto;

import com.project.todolist.entity.ToDoItems;
import com.project.todolist.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListDto {
 private int id;
 private String name;
 private User user;
 private List<ToDoItems> toDoItemsList;
}
