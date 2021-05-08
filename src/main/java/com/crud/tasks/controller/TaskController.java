package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@RequestMapping("/v1/task" )
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;



    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    @GetMapping("getTask/{id}")
    public TaskDto getTask(@PathVariable Long id) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                service.getTaskByID(id).orElseThrow(TaskNotFoundException::new));

    }

    @RequestMapping(method = RequestMethod.DELETE, value ="deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);

    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@ RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
         service.saveTask(task);

    }
}
