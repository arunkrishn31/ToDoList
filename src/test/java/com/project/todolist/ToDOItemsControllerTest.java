package com.project.todolist;

import com.project.todolist.controller.ToDoItemsController;
import com.project.todolist.dto.ToDoItemsDto;
import com.project.todolist.service.ToDoItemsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ToDOItemsControllerTest.class)
public class ToDOItemsControllerTest {
    @InjectMocks
    private ToDoItemsController toDoItemsController;

    @Mock
    private ToDoItemsService toDoItemsService;

    @Test
    public void testCreateToDoListItems() {
        int toDoListId = 1;
        ToDoItemsDto toDoItemsDto = new ToDoItemsDto();
        toDoItemsDto.setId(1);
        toDoItemsDto.setTitle("ToDo1");
        toDoItemsDto.setCompleted(String.valueOf(false));

        when(toDoItemsService.createnewtodolist(toDoListId, toDoItemsDto)).thenReturn(toDoItemsDto);

        ResponseEntity<ToDoItemsDto> result = toDoItemsController.createToDoListItems(toDoListId, toDoItemsDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(toDoItemsDto, result.getBody());
    }

    @Test
    public void testGetAllToDoitems() {
        int toDoListId = 1;
        ToDoItemsDto toDoItemsDto = new ToDoItemsDto();
        toDoItemsDto.setId(1);
        toDoItemsDto.setTitle("ToDo1");
        toDoItemsDto.setCompleted(String.valueOf(false));
        List<ToDoItemsDto> toDoItemsDtos = new ArrayList<>();
        toDoItemsDtos.add(toDoItemsDto);

        when(toDoItemsService.getAllTodoitemsbyUserId(toDoListId)).thenReturn(toDoItemsDtos);

        ResponseEntity<List<ToDoItemsDto>> result = toDoItemsController.getAllToDoitems(toDoListId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoItemsDtos, result.getBody());
    }

    @Test
    public void testGettodoitemsbytodolistid() {
        int toDoListId = 1;
        int toDoItemId = 1;
        ToDoItemsDto toDoItemsDto = new ToDoItemsDto();
        toDoItemsDto.setId(toDoItemId);
        toDoItemsDto.setTitle("ToDo1");
        toDoItemsDto.setCompleted(String.valueOf(false));

        when(toDoItemsService.getodoitemsByuserid(toDoListId, toDoItemId)).thenReturn(toDoItemsDto);

        ResponseEntity<ToDoItemsDto> result = toDoItemsController.gettodoitemsbytodolistid(toDoListId, toDoItemId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoItemsDto, result.getBody());
    }


    @Test
    public void testUpdatetodoitemsbytodolistid() {
        int toDoListId = 1;
        int toDoItemId = 1;
        ToDoItemsDto toDoItemsDto = new ToDoItemsDto();
        toDoItemsDto.setId(toDoItemId);
        toDoItemsDto.setTitle("ToDo5");
        toDoItemsDto.setCompleted(String.valueOf(true));

        when(toDoItemsService.updateTodoitemsbyuserId(toDoListId, toDoItemId, toDoItemsDto)).thenReturn(toDoItemsDto);

        ResponseEntity<ToDoItemsDto> result = toDoItemsController.updatetodoitemsbytodolistid(toDoListId, toDoItemId, toDoItemsDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoItemsDto, result.getBody());
    }

    @Test
    public void testDeletetodoitemsbyusingtodolistId() {
        int toDoListId = 1;
        int toDoItemId = 1;

        ResponseEntity<String> result = toDoItemsController.deletetodoitemsbyusingtodolistId(toDoListId, toDoItemId);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertEquals("ToDoItems deleted Successfully", result.getBody());
        verify(toDoItemsService).deleteToDoListById(toDoListId, toDoItemId);
    }
}

