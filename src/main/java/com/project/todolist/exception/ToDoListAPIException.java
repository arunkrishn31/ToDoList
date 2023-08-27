package com.project.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ToDoListAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;
}