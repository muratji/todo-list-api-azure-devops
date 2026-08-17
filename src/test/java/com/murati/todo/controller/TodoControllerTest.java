package com.murati.todo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    @DisplayName("GET /todoapi/todos - Should return list of todos")
    void shouldReturnListOfTodos() throws Exception {
        mockMvc.perform(get("/todoapi/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(2356L))
                .andExpect(jsonPath("$[0].title").value("Todo title"))
                .andExpect(jsonPath("$[0].description").value("Todo Description"))
                .andExpect(jsonPath("$[0].priority").value("LOW"))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[0].dueDate").exists())
                .andExpect(jsonPath("$[0].createdAt").exists());
    }

    @Test
    @DisplayName("GET /todoapi/todos - Should return 200 status")
    void shouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/todoapi/todos"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /todoapi/todos - Should return valid JSON")
    void shouldReturnValidJson() throws Exception {
        mockMvc.perform(get("/todoapi/todos"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /todoapi/todos - Should return non-empty list")
    void shouldReturnNonEmptyList() throws Exception {
        mockMvc.perform(get("/todoapi/todos"))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    @DisplayName("GET /todoapi/todos - Should have correct todo structure")
    void shouldHaveCorrectTodoStructure() throws Exception {
        mockMvc.perform(get("/todoapi/todos"))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].title").isString())
                .andExpect(jsonPath("$[0].description").isString())
                .andExpect(jsonPath("$[0].priority").isString())
                .andExpect(jsonPath("$[0].completed").isBoolean())
                .andExpect(jsonPath("$[0].dueDate").isNotEmpty())
                .andExpect(jsonPath("$[0].createdAt").isNotEmpty());
    }
}