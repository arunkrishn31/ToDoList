package com.project.todolist;

import com.project.todolist.dto.ToDoListDto;
import com.project.todolist.entity.ToDoList;
import com.project.todolist.entity.User;
import com.project.todolist.repository.ToDoListRepository;
import com.project.todolist.repository.UserRepository;
import com.project.todolist.service.ToDoListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes=ToDoListServiceTest.class)
public class ToDoListServiceTest {
    @InjectMocks
    private ToDoListService toDoListService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ToDoListRepository toDoListRepo;

    @Mock
    private ModelMapper modelMapper;


    @Test
    public void testCreateNewToDoList() {
        int id = 1;
        User user = new User();
        user.setId(id);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(id);
        toDoListDto.setName("Test");
        toDoListDto.setUser(user);
        toDoListDto.setToDoItemsList(new ArrayList<>());

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        ToDoList toDoList = new ToDoList();
        when(modelMapper.map(toDoListDto, ToDoList.class)).thenReturn(toDoList);
        when(toDoListRepo.save(toDoList)).thenReturn(toDoList);
        when(modelMapper.map(toDoList, ToDoListDto.class)).thenReturn(toDoListDto);

        ToDoListDto result = toDoListService.createnewtodolist(id, toDoListDto);
        assertEquals(toDoListDto, result);
    }

    @Test
    public void testGetToDoListByUserId() {
        int userId = 1;
        int toDoListId = 1;
        User user = new User();
        user.setId(userId);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListId);
        toDoList.setUser(user);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(toDoListId);
        toDoListDto.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(toDoListRepo.findById(toDoListId)).thenReturn(Optional.of(toDoList));
        when(modelMapper.map(toDoList, ToDoListDto.class)).thenReturn(toDoListDto);

        ToDoListDto result = toDoListService.getodolistByuserid(userId, toDoListId);
        assertEquals(toDoListDto, result);
    }

    @Test
    public void testGetAllTodoListsByUserId() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(1);
        toDoList.setUser(user);
        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(1);
        toDoListDto.setUser(user);
        List<ToDoListDto> toDoListDtos = new ArrayList<>();
        toDoListDtos.add(toDoListDto);

        when(toDoListRepo.findByUserId(userId)).thenReturn(toDoLists);
        when(modelMapper.map(toDoList, ToDoListDto.class)).thenReturn(toDoListDto);

        List<ToDoListDto> result = toDoListService.getAllTodoListsbyUserId(userId);
        assertEquals(toDoListDtos, result);
    }



    @Test
    public void testDeleteToDoListById() {
        int userId = 1;
        int toDoListId = 1;
        User user = new User();
        user.setId(userId);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(toDoListId);
        toDoList.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(toDoListRepo.findById(toDoListId)).thenReturn(Optional.of(toDoList));

        toDoListService.deleteToDoListById(userId, toDoListId);
        verify(toDoListRepo).deleteById(toDoListId);
    }
}
