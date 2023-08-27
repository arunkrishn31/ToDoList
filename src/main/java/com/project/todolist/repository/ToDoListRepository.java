package com.project.todolist.repository;

import com.project.todolist.dto.ToDoListDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todolist.entity.ToDoList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer>{
    List<ToDoList> findByUserId(int userid);
}
