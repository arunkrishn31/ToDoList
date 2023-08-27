package com.project.todolist;

import com.project.todolist.dto.ToDoItemsDto;
import com.project.todolist.entity.ToDoItems;
import com.project.todolist.entity.ToDoList;
import com.project.todolist.repository.ToDoItemsRepository;
import com.project.todolist.repository.ToDoListRepository;
import com.project.todolist.service.ToDoItemsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ToDoItemsServiceTest.class)
public class ToDoItemsServiceTest {
    @InjectMocks
    private ToDoItemsService toDoItemsService;

    @Mock
    private ToDoListRepository toDoListRepository;

    @Mock
    private ToDoItemsRepository toDoItemRepo;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreatenewtodolist() {
        int toDoListId = 1;
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListId);
        ToDoItemsDto toDoItemsDto = new ToDoItemsDto();
        toDoItemsDto.setId(1);
        toDoItemsDto.setTitle("ToDo1");
        toDoItemsDto.setCompleted(String.valueOf(false));
        ToDoItems toDoItems = new ToDoItems();
        toDoItems.setId(1);
        toDoItems.setTitle("ToDo1");
        toDoItems.setCompleted(String.valueOf(false));

        when(toDoListRepository.findById(toDoListId)).thenReturn(Optional.of(toDoList));
        when(modelMapper.map(toDoItemsDto, ToDoItems.class)).thenReturn(toDoItems);
        when(toDoItemRepo.save(toDoItems)).thenReturn(toDoItems);
        when(modelMapper.map(toDoItems, ToDoItemsDto.class)).thenReturn(toDoItemsDto);

        ToDoItemsDto result = toDoItemsService.createnewtodolist(toDoListId, toDoItemsDto);
        assertEquals(toDoItemsDto, result);
    }



    @Test
    public void testGetodoitemsByuserid() {
        int toDoListId = 1;
        int toDoItemId = 1;
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListId);
        ToDoItems toDoItems = new ToDoItems();
        toDoItems.setId(toDoItemId);
        toDoItems.setToDoList(toDoList);
        ToDoItemsDto toDoItemsDto = new ToDoItemsDto();
        toDoItemsDto.setId(toDoItemId);
        toDoItemsDto.setTitle("ToDo1");
        toDoItemsDto.setCompleted(String.valueOf(false));

        when(toDoListRepository.findById(toDoListId)).thenReturn(Optional.of(toDoList));
        when(toDoItemRepo.findById(toDoItemId)).thenReturn(Optional.of(toDoItems));
        when(modelMapper.map(toDoItems, ToDoItemsDto.class)).thenReturn(toDoItemsDto);

        ToDoItemsDto result = toDoItemsService.getodoitemsByuserid(toDoListId, toDoItemId);
        assertEquals(toDoItemsDto, result);
    }


    @Test
    public void testDeleteToDoListById() {
        int toDoListId = 1;
        int toDoItemId = 1;
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListId);
        ToDoItems toDoItems = new ToDoItems();
        toDoItems.setId(toDoItemId);
        toDoItems.setToDoList(toDoList);

        when(toDoListRepository.findById(toDoListId)).thenReturn(Optional.of(toDoList));
        when(toDoItemRepo.findById(toDoItemId)).thenReturn(Optional.of(toDoItems));

        toDoItemsService.deleteToDoListById(toDoListId, toDoItemId);
        verify(toDoItemRepo).deleteById(toDoItemId);
    }
}
