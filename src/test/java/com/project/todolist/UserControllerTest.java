package com.project.todolist;

import com.project.todolist.controller.UserController;
import com.project.todolist.dto.UserDto;
import com.project.todolist.entity.User;
import com.project.todolist.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserControllerTest.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");

        when(userService.createUser(userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto> expectedUsers = List.of(new UserDto(1, "Abhijith", "Abhijith@example.com", "password", "ROLE_USER", new ArrayList<>()),
                new UserDto(2, "Arun", "Arun@example.com", "password", "ROLE_ADMIN", new ArrayList<>()));
        when(userService.getAllUsers()).thenReturn(expectedUsers);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
    }

    @Test
    public void testGetUserbyId() {
        int userId = 1;
        UserDto expectedUser = new UserDto(1, "Arun", "Arun@example.com", "password", "ROLE_ADMIN", new ArrayList<>());
        when(userService.getUserbyId(userId)).thenReturn(expectedUser);

        ResponseEntity<UserDto> response = userController.getUserbyId(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void testUpdateUserbyId() {
        int userId = 1;
        UserDto userDto = new UserDto(1, "John Doe", "john.doe@example.com", "password", "USER",new ArrayList<>());
        UserDto expectedUser = new UserDto(1, "John Doe", "john.doe@example.com", "new_password", "ADMIN", new ArrayList<>());
        when(userService.updateUserById(userId, userDto)).thenReturn(expectedUser);

        ResponseEntity<UserDto> response = userController.updateUserbyId(userId, userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void testDeleteUserbyId() {
        int userId = 1;
        doNothing().when(userService).deleteUserById(userId);

        ResponseEntity<String> response = userController.DeleteUserbyId(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Successfully deleted", response.getBody());
    }
}
