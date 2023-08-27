package com.project.todolist.service;

import com.project.todolist.dto.ToDoListDto;
import com.project.todolist.entity.ToDoList;
import com.project.todolist.entity.User;
import com.project.todolist.exception.ResourceNotFoundException;
import com.project.todolist.exception.ToDoListAPIException;
import com.project.todolist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.project.todolist.repository.ToDoListRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ToDoListService {
	@Autowired
	private ToDoListRepository toDoListRepo;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ToDoListDto createnewtodolist(int id, ToDoListDto toDoListDto) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", +id));
		ToDoList toDoList = modelMapper.map(toDoListDto, ToDoList.class);
		toDoList.setUser(user);
		ToDoList savetodolist = toDoListRepo.save(toDoList);
		return modelMapper.map(savetodolist,ToDoListDto.class);
	}

	public ToDoListDto getodolistByuserid(int userid, int todolistid) {
		User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", +userid));
		ToDoList toDoList = toDoListRepo.findById(todolistid).orElseThrow(() -> new ResourceNotFoundException("todolist", "id", +todolistid));
		if(!Objects.equals(toDoList.getUser().getId(), user.getId())){
			throw new ToDoListAPIException(HttpStatus.BAD_REQUEST, "this todolist doesn't belongs to this User");
		}
		return  modelMapper.map(toDoList,ToDoListDto.class);
	}
	public List<ToDoListDto> getAllTodoListsbyUserId(int userid) {
		List<ToDoList> toDoLists = toDoListRepo.findByUserId(userid);
		return toDoLists.stream()
				.map(toDoList -> modelMapper.map(toDoList, ToDoListDto.class))
				.collect(Collectors.toList());
	}


	public ToDoListDto updateTodolistbyuserId(int userid, int todoListid, ToDoListDto toDoListDto) {
		User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", +userid));
		ToDoList toDoList = toDoListRepo.findById(todoListid).orElseThrow(() -> new ResourceNotFoundException("todolist", "id", +todoListid));
		if(!Objects.equals(toDoList.getUser().getId(), user.getId())){
			throw new ToDoListAPIException(HttpStatus.BAD_REQUEST, "this todolist doesn't belongs to this User");
		}
		toDoList.setName(toDoListDto.getName());
		toDoList.setUser(toDoListDto.getUser());
		toDoList.setToDoItemsList(toDoListDto.getToDoItemsList());
		ToDoList savedtoDoList = toDoListRepo.save(toDoList);
		return modelMapper.map(savedtoDoList,ToDoListDto.class);
	}

	public void deleteToDoListById(int userid, int todolistid) {
		User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", +userid));
		ToDoList toDoList = toDoListRepo.findById(todolistid).orElseThrow(() -> new ResourceNotFoundException("todolist", "id", +todolistid));
		if(!Objects.equals(toDoList.getUser().getId(), user.getId())){
			throw new ToDoListAPIException(HttpStatus.BAD_REQUEST, "this todolist doesn't belongs to this User");
		}
		toDoListRepo.deleteById(todolistid);
	}
}
