package com.project.todolist;

import com.project.todolist.dto.UserDto;
import com.project.todolist.entity.User;
import com.project.todolist.repository.UserRepository;
import com.project.todolist.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserServiceTest.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepo;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setName("Abhijith");
        User user = new User();
        user.setName(userDto.getName());

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.createUser(userDto);

        assertEquals(userDto.getName(), result.getName());
    }

    @Test
    void testGetAllUsers() {
        UserDto userDto1 = new UserDto();
        userDto1.setName("Abhijith");
        userDto1.setRole("ROLE_ADMIN");
        User user1 = new User();
        user1.setName(userDto1.getName());
        user1.setRole(userDto1.getRole());

        UserDto userDto2 = new UserDto();
        userDto2.setName("Arun");
        userDto2.setRole("ROLE_USER");
        User user2 = new User();
        user2.setName(userDto2.getName());
        user2.setRole(user2.getRole());

        List<User> users = Arrays.asList(user1, user2);
        List<UserDto> userDtos = Arrays.asList(userDto1, userDto2);

        when(userRepo.findAll()).thenReturn(users);
        when(modelMapper.map(user1, UserDto.class)).thenReturn(userDto1);
        when(modelMapper.map(user2, UserDto.class)).thenReturn(userDto2);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtos.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(userDtos.get(i).getName(), result.get(i).getName());
            assertEquals(userDtos.get(i).getRole(), result.get(i).getRole());
        }
    }
    @Test
    public void testGetUserById() {
        int id = 1;
        UserDto userDto = new UserDto();
        userDto.setName("Abhijith");
        User user = new User();
        user.setId(id);
        user.setName(userDto.getName());

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.getUserbyId(id);

        assertEquals(userDto.getName(), result.getName());
    }
    @Test
    public void testUpdateUserById() {
        int id = 1;
        UserDto userDto = new UserDto();
        userDto.setName("Abhijith");
        userDto.setEmail("abhijith@gmail.com");
        userDto.setPassword("Abhi");
        userDto.setRole("ROLE_USER");
        User user = new User();
        user.setId(id);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.updateUserById(id, userDto);

        assertEquals(userDto.getName(), result.getName());
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getPassword(), result.getPassword());
        assertEquals(userDto.getRole(), result.getRole());
    }

    @Test
    public void testDeleteUserById() {
        int id = 1;
        User user = new User();
        user.setId(id);

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        userService.deleteUserById(id);
        verify(userRepo).deleteById(id);
    }
}
