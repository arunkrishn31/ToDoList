package com.project.todolist.service;

import com.project.todolist.dto.ToDoItemsDto;
import com.project.todolist.entity.ToDoItems;
import com.project.todolist.entity.ToDoList;
import com.project.todolist.exception.ResourceNotFoundException;
import com.project.todolist.exception.ToDoListAPIException;
import com.project.todolist.repository.ToDoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.todolist.repository.ToDoItemsRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ToDoItemsService {
	@Autowired
	private ToDoItemsRepository toDoItemRepo;
    @Autowired
    private ToDoListRepository toDoListRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ToDoItemsDto createnewtodolist(int todolistid, ToDoItemsDto toDoItemsDto) {
        ToDoList toDoList = toDoListRepository.findById(todolistid).orElseThrow(() -> new ResourceNotFoundException("ToDoList", "id", +todolistid));
        ToDoItems toDoItems = modelMapper.map(toDoItemsDto, ToDoItems.class);
        toDoItems.setToDoList(toDoList);
        ToDoItems saveddoItems = toDoItemRepo.save(toDoItems);
        return modelMapper.map(saveddoItems,ToDoItemsDto.class);
    }

    public List<ToDoItemsDto> getAllTodoitemsbyUserId(int todolistid) {
       List<ToDoItemsDto>todoListitems= toDoItemRepo.findByToDoListId(todolistid);
       return  todoListitems.stream().map(items->modelMapper.map(items,ToDoItemsDto.class))
               .collect(Collectors.toList());
    }

    public ToDoItemsDto getodoitemsByuserid(int todolistid, int todoitemsid) {
        ToDoList toDoList = toDoListRepository.findById(todolistid).orElseThrow(() -> new ResourceNotFoundException("ToDoListID", "id", +todolistid));
        ToDoItems toDoItems = toDoItemRepo.findById(todoitemsid).orElseThrow(() -> new ResourceNotFoundException("ToDoItemsId", "id", +todoitemsid));
        if(!Objects.equals(toDoItems.getToDoList().getId(),toDoList.getId())){
            throw new ToDoListAPIException(HttpStatus.BAD_REQUEST, "this todoItems doesn't belongs to this ToDoList");
        }
        return modelMapper.map(toDoItems,ToDoItemsDto.class);
    }

    public ToDoItemsDto updateTodoitemsbyuserId(int todolistid, int todoitemsid, ToDoItemsDto toDoitemsdto) {
        ToDoList toDoList = toDoListRepository.findById(todolistid).orElseThrow(() -> new ResourceNotFoundException("ToDoListID", "id", +todolistid));
        ToDoItems toDoItems = toDoItemRepo.findById(todoitemsid).orElseThrow(() -> new ResourceNotFoundException("ToDoItemsId", "id", +todoitemsid));
        if(!Objects.equals(toDoItems.getToDoList().getId(),toDoList.getId())){
            throw new ToDoListAPIException(HttpStatus.BAD_REQUEST, "this todoItems doesn't belongs to this ToDoList");
        }
        toDoItems.setTitle(toDoitemsdto.getTitle());
        toDoItems.setDescription(toDoitemsdto.getDescription());
        toDoItems.setDueDate(toDoitemsdto.getDueDate());
        toDoItems.setDueDate(toDoitemsdto.getDueDate());
        toDoItems.setToDoList(toDoitemsdto.getToDoList());
        ToDoItems updateddoItems = toDoItemRepo.save(toDoItems);
        return modelMapper.map(updateddoItems,ToDoItemsDto.class);
    }

    public void deleteToDoListById(int todolistid, int todoitemsid) {
        ToDoList toDoList = toDoListRepository.findById(todolistid).orElseThrow(() -> new ResourceNotFoundException("ToDoListID", "id", +todolistid));
        ToDoItems toDoItems = toDoItemRepo.findById(todoitemsid).orElseThrow(() -> new ResourceNotFoundException("ToDoItemsId", "id", +todoitemsid));
        if(!Objects.equals(toDoItems.getToDoList().getId(),toDoList.getId())){
            throw new ToDoListAPIException(HttpStatus.BAD_REQUEST, "this todoItems doesn't belongs to this ToDoList");
        }
        toDoItemRepo.deleteById(todoitemsid);
    }
}
