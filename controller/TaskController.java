package com.eTaskifyAPI.etaskify.controller;

import com.eTaskifyAPI.etaskify.dto.TaskDto;
import com.eTaskifyAPI.etaskify.model.Task;
import com.eTaskifyAPI.etaskify.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskService.createTask(taskDto);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Task>> getAllTask(@PathVariable Long userId) {
        List<Task> tasks = taskService.getAllTask(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{gorevId}/assign")
    public ResponseEntity<Task> assignTask(@PathVariable Long taskId, @RequestBody List<Long> userIds) {
        return ResponseEntity.ok(taskService.assignTask(taskId, userIds));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDto));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, taskDto));
    }
}




