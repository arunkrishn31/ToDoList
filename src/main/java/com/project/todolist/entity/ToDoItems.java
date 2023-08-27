package com.project.todolist.entity;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  @Column(name="Title",nullable = false)
	  private String title;
	  @Column(name="Description",nullable = false)
	  private String description;
	  @Column(name="Completed",nullable = false)
	  private String completed;
	  @Column(name="DueDate",nullable = false)
	  private Date dueDate;
	  @JsonIgnore
	  @ManyToOne(fetch =FetchType.LAZY)
	  @JoinColumn(name = "to_do_list_id")
	  private ToDoList toDoList;
}
