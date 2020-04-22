package com.crud.tasks.controller;

import com.crud.tasks.domain.task.Task;
import com.crud.tasks.domain.task.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    private final static String controllerUrl = "/v1/tasks";
    private static List<TaskDto> taskDtoList;
    private static TaskDto taskDto;
    private static Task task;
    private static List<Task> taskList;


    @BeforeAll
    public static void init() {
        taskDtoList = new ArrayList<>();
        taskDto = new TaskDto(1L, "Dto title", "dto content");
        taskDtoList.add(taskDto);

        taskList = new ArrayList<>();
        task = new Task(2L, "title", "content");
        taskList.add(task);
    }

    @Test
    void shouldGetTaskDtoList() throws Exception {
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        mockMvc.perform(get(controllerUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$[0].content", is(taskDto.getContent())));
    }

    @Test
    void shouldGetTaskDto() throws Exception {
        when(dbService.getTask(anyLong())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);

        mockMvc.perform(get(controllerUrl + "/{taskId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$.content", is(taskDto.getContent())));
    }
    
    @Test
    void shouldDeleteTaskAndReturnStatusOK() throws Exception {
        mockMvc.perform(delete(controllerUrl + "/{taskId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnTaskDtoWhenUpdate() throws Exception {
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String taskDtoJson = gson.toJson(taskDto);

        mockMvc.perform(put(controllerUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(taskDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is(taskDto.getTitle())))
                .andExpect(jsonPath("$.content", is(taskDto.getContent())));

    }

    @Test
    void shouldReturnTaskDtoWhenCreate() throws Exception {
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String taskDtoJson = gson.toJson(taskDto);

        mockMvc.perform(post(controllerUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(taskDtoJson))
                .andExpect(status().isOk());
    }
}