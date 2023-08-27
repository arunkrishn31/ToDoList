package com.project.todolist.controller;

import com.project.todolist.dto.ToDoItemsDto;
import com.project.todolist.dto.ToDoListDto;
import com.project.todolist.service.ToDoItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoitems")
@PreAuthorize("hasRole('ROLE_USER')")
public class ToDoItemsController {
    @Autowired
    private ToDoItemsService toDoItemsService;

    @PostMapping("/todolist/{todolistid}/todoitems")
    public ResponseEntity<ToDoItemsDto> createToDoListItems(@PathVariable("todolistid") int todolistid, @RequestBody ToDoItemsDto toDoItemsDto){
        ToDoItemsDto createnewtodoitems = toDoItemsService.createnewtodolist(todolistid, toDoItemsDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createnewtodoitems);
    }
    @GetMapping("/todolist/{todolistid}/todoitems")
    public ResponseEntity<List<ToDoItemsDto>>getAllToDoitems(@PathVariable("todolistid") int todolistid){
        List<ToDoItemsDto> all=toDoItemsService.getAllTodoitemsbyUserId(todolistid);
        return  ResponseEntity.status(HttpStatus.OK).body(all);
    }
    @GetMapping("/todolist/{todolistid}/todoitems/{todoitemsid}")
    public ResponseEntity<ToDoItemsDto>gettodoitemsbytodolistid(@PathVariable ("todolistid")int todolistid, @PathVariable ("todoitemsid")int todoitemsid){
        ToDoItemsDto getodoitemsByuseridById = toDoItemsService.getodoitemsByuserid(todolistid, todoitemsid);
        return  ResponseEntity.status(HttpStatus.OK).body(getodoitemsByuseridById);
    }
    @PutMapping("/todolist/{todolistid}/todoitems/{todoitemsid}")
    public ResponseEntity<ToDoItemsDto>updatetodoitemsbytodolistid(@PathVariable ("todolistid")int todolistid,
                                                                  @PathVariable ("todoitemsid")int todoitemsid,
                                                                  @RequestBody ToDoItemsDto toDoitemsdto){
        ToDoItemsDto updatedTodoitemsbyuserIdById = toDoItemsService.updateTodoitemsbyuserId(todolistid, todoitemsid, toDoitemsdto);
        return  ResponseEntity.status(HttpStatus.OK).body(updatedTodoitemsbyuserIdById);
    }
    @DeleteMapping("/todolist/{todolistid}/todoitems/{todoitemsid}")
    public ResponseEntity<String>deletetodoitemsbyusingtodolistId(@PathVariable ("todolistid") int todolistid,
                                                        @PathVariable("todoitemsid") int todoitemsid){
        toDoItemsService.deleteToDoListById(todolistid,todoitemsid);
        return  new ResponseEntity<>("ToDoItems deleted Successfully",HttpStatus.NO_CONTENT);
    }

}
