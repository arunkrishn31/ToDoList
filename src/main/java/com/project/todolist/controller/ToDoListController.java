package com.project.todolist.controller;

import com.project.todolist.dto.ToDoItemsDto;
import com.project.todolist.dto.ToDoListDto;
import com.project.todolist.entity.ToDoList;
import com.project.todolist.service.ToDoListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ToDoListController {
    private ToDoListService toDoListService;

    @PostMapping("/user/{userid}/todolist")
    public ResponseEntity<ToDoListDto>createToDoList(@PathVariable("userid") int id, @RequestBody ToDoListDto toDoListDto){
        ToDoListDto createnewtodolist = toDoListService.createnewtodolist(id, toDoListDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createnewtodolist);
    }
    @GetMapping("/user/{userid}todolist")
    public ResponseEntity<List<ToDoListDto>>getAllToDoList(@PathVariable("userid") int userid){
        List<ToDoListDto> all=toDoListService.getAllTodoListsbyUserId(userid);
        return  ResponseEntity.status(HttpStatus.OK).body(all);
    }
    @GetMapping("/user/{userid}/todolist/{todolistid}")
    public ResponseEntity<ToDoListDto>getCommentById(@PathVariable ("userid")int userid, @PathVariable ("todolistid")int todolistid){
        ToDoListDto getodolistByuseridById = toDoListService.getodolistByuserid(userid, todolistid);
        return  ResponseEntity.status(HttpStatus.OK).body(getodolistByuseridById);
    }

    @PutMapping("/user/{userid}/todolist/{todolistid}")
    public ResponseEntity<ToDoListDto>updateCommentgetCommentById(@PathVariable ("userid")int userid,
                                                                 @PathVariable ("todolistid")int todoListid,
                                                                 @RequestBody ToDoListDto toDoListDto){
        ToDoListDto updatedTodolistbyuserIdById = toDoListService.updateTodolistbyuserId(userid, todoListid, toDoListDto);
        return  ResponseEntity.status(HttpStatus.OK).body(updatedTodolistbyuserIdById);
    }

    @DeleteMapping("/user/{userid}/todolist/{todolistid}")
    public ResponseEntity<String>deleteCommentbyusingId(@PathVariable ("userid") int userid,
                                                        @PathVariable("todolistid") int todolistid){
        toDoListService.deleteToDoListById(userid,todolistid);
        return  new ResponseEntity<>("ToDoList deleted Successfully",HttpStatus.NO_CONTENT);
    }

}
