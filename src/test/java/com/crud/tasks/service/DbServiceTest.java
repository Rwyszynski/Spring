package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    DbService dbService;

    @Test
    void shouldGetAllTasks() {
        //Given

        List<Task> tasks = List.of(
                new Task(1l, "test", "test2"),
                new Task(2l, "test", "test3"),
                new Task(3l, "test", "test4")
        );
        //When
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> fetchTasks = dbService.getAllTasks();

        //Then
        assertEquals(3, fetchTasks.size());
    }

    @Test
    void shouldSaveTasks() {
        //Given
        Task task = new Task(1l, "test", "test2");

        //When
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        String result = dbService.saveTask(task).getTitle();

        //Then
        assertEquals("test", result);
    }

    @Test
    void shouldDeleteTasks() throws TaskNotFoundException {
        //Given
        Task task = new Task(1l, "test", "test2");

        //When
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Mockito.doReturn(List.of(task)).when(taskRepository).findAll();
        Mockito.doNothing().when(taskRepository).deleteById(1L);

        // When
        List<Task> beforeDelete = dbService.getAllTasks();
        Mockito.doReturn(List.of()).when(taskRepository).findAll();
        dbService.deleteTask(1L);
        List<Task> afterDelete = dbService.getAllTasks();

        //Then
        assertEquals(1, beforeDelete.size());
        assertEquals(0, afterDelete.size());
    }

    @Test
    void shouldGetTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(1l, "test", "test2");

        //When
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Task result = dbService.getTask(task.getId());

        //Then
        assertEquals("test", result.getTitle());
    }
}













