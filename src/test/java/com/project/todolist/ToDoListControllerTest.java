package com.project.todolist;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.project.todolist.controller.ToDoListController;
import com.project.todolist.dto.ToDoListDto;
import com.project.todolist.entity.User;
import com.project.todolist.service.ToDoListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = ToDoListControllerTest.class)
public class ToDoListControllerTest {

    @InjectMocks
    private ToDoListController toDoListController;

    @Mock
    private ToDoListService toDoListService;

    @Test
    public void testCreateToDoList() {
        int id = 1;
        User user = new User();
        user.setId(id);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(id);
        toDoListDto.setName("Test");
        toDoListDto.setUser(user);
        toDoListDto.setToDoItemsList(new ArrayList<>());

        when(toDoListService.createnewtodolist(id, toDoListDto)).thenReturn(toDoListDto);

        ResponseEntity<ToDoListDto> result = toDoListController.createToDoList(id, toDoListDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(toDoListDto, result.getBody());
    }

    @Test
    public void testGetAllToDoList() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(1);
        toDoListDto.setName("Test");
        toDoListDto.setUser(user);
        toDoListDto.setToDoItemsList(new ArrayList<>());
        List<ToDoListDto> toDoListDtos = new ArrayList<>();
        toDoListDtos.add(toDoListDto);

        when(toDoListService.getAllTodoListsbyUserId(userId)).thenReturn(toDoListDtos);

        ResponseEntity<List<ToDoListDto>> result = toDoListController.getAllToDoList(userId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoListDtos, result.getBody());
    }

    @Test
    public void testGetCommentById() {
        int userId = 1;
        int toDoListId = 1;
        User user = new User();
        user.setId(userId);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(toDoListId);
        toDoListDto.setName("Abhijith");
        toDoListDto.setUser(user);

        when(toDoListService.getodolistByuserid(userId, toDoListId)).thenReturn(toDoListDto);

        ResponseEntity<ToDoListDto> result = toDoListController.getCommentById(userId, toDoListId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoListDto, result.getBody());
    }

    @Test
    public void testUpdateCommentgetCommentById() {
        int userId = 1;
        int toDoListId = 1;
        User user = new User();
        user.setId(userId);
        ToDoListDto toDoListDto = new ToDoListDto();
        toDoListDto.setId(toDoListId);
        toDoListDto.setName("Updated Name");
        toDoListDto.setUser(user);

        when(toDoListService.updateTodolistbyuserId(userId, toDoListId, toDoListDto)).thenReturn(toDoListDto);

        ResponseEntity<ToDoListDto> result = toDoListController.updateCommentgetCommentById(userId, toDoListId, toDoListDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoListDto, result.getBody());
    }

    @Test
    public void testDeleteCommentbyusingId() {
        int userId = 1;
        int toDoListId = 1;

        ResponseEntity<String> result = toDoListController.deleteCommentbyusingId(userId, toDoListId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertEquals("ToDoList deleted Successfully", result.getBody());
        verify(toDoListService).deleteToDoListById(userId, toDoListId);
    }
}

