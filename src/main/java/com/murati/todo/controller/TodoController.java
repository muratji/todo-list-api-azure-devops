package com.murati.todo.controller;

import com.murati.todo.dto.TodoResponseDto;
import com.murati.todo.entity.TodoItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todoapi/todos")
public class TodoController {

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        var todo = TodoResponseDto
                .builder()
                .id(2356L)
                .title("Todo title")
                .description("Todo Description")
                .priority(TodoItem.Priority.LOW)
                .completed(false)
                .dueDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(List.of(todo));
    }
}
