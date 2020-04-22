package com.crud.tasks.mapper;

import com.crud.tasks.domain.task.Task;
import com.crud.tasks.domain.task.TaskDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {
    @Autowired
    TaskMapper taskMapper;
    private static List<Task> taskList;
    private static Task task;
    private static TaskDto taskDto;

    @BeforeAll
    static void setUp() {
        taskList = new ArrayList<>();
        task = new Task(1L, "task title", "task content");
        taskList.add(task);
        
        taskDto = new TaskDto(2L, "task dto title", "task dto content");
    }

    @Test
    void mapToTask() {
        Task taskFromMapper = taskMapper.mapToTask(taskDto);
        
        assertAll(
                () -> assertEquals(taskDto.getId(), taskFromMapper.getId()),
                () -> assertEquals(taskDto.getTitle(), taskFromMapper.getTitle()),
                () -> assertEquals(taskDto.getContent(), taskFromMapper.getContent())
        );
    }

    @Test
    void mapToTaskDto() {
        TaskDto taskDtoFromMapper = taskMapper.mapToTaskDto(task);

        assertAll(
                () -> assertEquals(task.getId(), taskDtoFromMapper.getId()),
                () -> assertEquals(task.getTitle(), taskDtoFromMapper.getTitle()),
                () -> assertEquals(task.getContent(), taskDtoFromMapper.getContent())
        );
    }

    @Test
    void mapToTaskDtoList() {
        List<TaskDto> taskDtoListFromMapper = taskMapper.mapToTaskDtoList(taskList);
        
        assertAll(
                () -> assertEquals(taskList.get(0).getId(), taskDtoListFromMapper.get(0).getId()),
                () -> assertEquals(taskList.get(0).getTitle(), taskDtoListFromMapper.get(0).getTitle()),
                () -> assertEquals(taskList.get(0).getContent(), taskDtoListFromMapper.get(0).getContent())
        );
    }
}