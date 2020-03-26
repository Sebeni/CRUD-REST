package com.crud.tasks.service;

import com.crud.tasks.domain.task.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {
    @Autowired
    DbService dbService;
    @Autowired
    TaskRepository taskRepository;

    private Task addedTask;

    @BeforeEach
    public void dbInit() {
        addedTask = taskRepository.save(new Task(null, "title", "content"));
    }

    @AfterEach
    public void dbCleanUp() {
        taskRepository.deleteById(addedTask.getId());
    }

    @Test
    void getAllTasks() {
        List<Task> tasksFromDb = dbService.getAllTasks();
        int lastIndex = tasksFromDb.size() - 1;
        System.out.println(lastIndex);

        assertAll(
                () -> assertEquals(taskRepository.count(), tasksFromDb.size()),
                () -> assertEquals(addedTask.getId(), tasksFromDb.get(lastIndex).getId()),
                () -> assertEquals(addedTask.getTitle(), tasksFromDb.get(lastIndex).getTitle()),
                () -> assertEquals(addedTask.getContent(), tasksFromDb.get(lastIndex).getContent())
        );
    }

    @Test
    void getTask() {
        Optional<Task> optionalTaskFromDb = dbService.getTask(addedTask.getId());
        assertNotNull(optionalTaskFromDb);

        Task taskFromDb = optionalTaskFromDb.get();

        assertAll(
                () -> assertEquals(addedTask.getId(), taskFromDb.getId()),
                () -> assertEquals(addedTask.getTitle(), taskFromDb.getTitle()),
                () -> assertEquals(addedTask.getContent(), taskFromDb.getContent())
        );
    }

    @Test
    void saveAndDeleteTask() {
        Task newTaskToAdd = new Task(null, "new task to add", "new content");
        long dbBeforeSave = taskRepository.count();
        dbService.saveTask(newTaskToAdd);
        
        assertEquals(dbBeforeSave + 1, dbService.getAllTasks().size());
        
        dbService.deleteTask(newTaskToAdd.getId());

        assertEquals(dbBeforeSave, dbService.getAllTasks().size());
    }
}