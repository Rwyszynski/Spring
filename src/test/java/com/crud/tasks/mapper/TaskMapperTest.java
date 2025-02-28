package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    @Test
    void shouldMapToTaskDtos() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "task", "task");
        Task task = new Task(1L, "task", "task");
        //When
        Task expectedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task, expectedTask);
    }

    @Test
    void shouldMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();

        List<Task> taskDtos = List.of(
                new Task(1L, "test", "tasktask"),
                new Task(1L, "task", "tasktask")
        );

        List<TaskDto> expected = List.of(
                new TaskDto(1L, "test", "tasktask"),
                new TaskDto(1L, "task", "tasktask")
        );


        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskDtos);

        //Then
        assertEquals(expected, result);
    }
}