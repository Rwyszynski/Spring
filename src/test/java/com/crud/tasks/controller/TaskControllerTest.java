package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void shouldFetchTasks() throws Exception {
        //Given
        List<Task> tasks = List.of(new Task(1L, "test", "test2"));
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        List<TaskDto> taskDtos2 = List.of(new TaskDto(1L, "test", "test2"));
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos2);

        //When & Then
        mvc
            .perform(MockMvcRequestBuilders
                    .get("/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON))
            // Task fields
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test2")));
    }


    @Test
    void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "test", "test2");
        TaskDto taskDto = new TaskDto(1L, "test", "test2");
        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);


        //When & Then
        mvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test2")));
    }

    @Test
    void shouldDeleteTask() throws Exception {


        //When & Then
        mvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        verify(dbService).deleteTask(1L);
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test", "test2");
        TaskDto taskDto = new TaskDto(1L, "test", "test2");
        Task newTask = new Task(1L, "test2", "test3");
        TaskDto newTaskDto = new TaskDto(1L, "test2", "test3");
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(newTask);
        when(taskMapper.mapToTaskDto(newTask)).thenReturn(newTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newTaskDto);

        //When & Then
        mvc
            .perform(MockMvcRequestBuilders
                    .put("/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test3")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test2");
        Task task = new Task(1L, "test", "test2");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        //When & Then
        mvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        verify(dbService).deleteTask(1L);
    }

}