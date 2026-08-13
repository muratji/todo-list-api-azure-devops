package com.murati.todo.dto;

import com.murati.todo.entity.TodoItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private TodoItem.Priority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
