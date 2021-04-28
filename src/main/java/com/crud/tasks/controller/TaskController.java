package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;



    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    @GetMapping("getTask/{id}")
    public Optional<Task> getTask(@PathVariable Long id) {
        Optional<Task> taskDto = service.getTaskByID(id);
        return taskDto;
    }

    @DeleteMapping("deleteTasks")
    public void deleteTask(Long taskId) {

    }
    @PutMapping("updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited task title", "Test content");
    }
    @PostMapping("createTask")
    public void createTask(TaskDto taskDto) {

    }
}
