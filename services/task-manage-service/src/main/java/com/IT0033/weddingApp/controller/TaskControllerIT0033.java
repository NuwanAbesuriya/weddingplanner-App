package com.IT0033.weddingApp.controller;

import com.IT0033.weddingApp.dto.TaskDTODIT0033;
import com.IT0033.weddingApp.dto.TaskResponseDTOIT0033;
import com.IT0033.weddingApp.entity.TaskStatus;
import com.IT0033.weddingApp.service.TaskServiceIT0033;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskControllerIT0033 {
    private final TaskServiceIT0033 taskService;


    @PostMapping
    public ResponseEntity<TaskResponseDTOIT0033> createTask(@Valid @RequestBody TaskDTODIT0033 taskDTO) {
        TaskResponseDTOIT0033 createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTOIT0033> getTaskById(@PathVariable Long id) {
        TaskResponseDTOIT0033 task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTOIT0033> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDTODIT0033 taskDTO) {
        TaskResponseDTOIT0033 updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTOIT0033>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskResponseDTOIT0033> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TaskResponseDTOIT0033>> getTasksByEventId(@PathVariable Long eventId) {
        List<TaskResponseDTOIT0033> tasks = taskService.getTasksByEventId(eventId);
        return ResponseEntity.ok(tasks);
    }
}
