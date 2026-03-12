package com.example.taskmanager.controller;

import com.example.taskmanager.enums.Priority;
import com.example.taskmanager.enums.Status;
import com.example.taskmanager.exception.BusinessException;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = service.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority
    ) {

        if (status != null && priority != null) {
            throw new BusinessException("Informe apenas um filtro por vez: status ou priority.");
        }

        if (status != null) {
            try {
                Status statusEnum = Status.valueOf(status.toUpperCase());
                return ResponseEntity.ok(service.getTasksByStatus(statusEnum));
            } catch (IllegalArgumentException e) {
                throw new BusinessException(
                        "Status inválido. Valores aceitos: PENDING, IN_PROGRESS, COMPLETED, CANCELLED"
                );
            }
        }

        if (priority != null) {
            try {
                Priority priorityEnum = Priority.valueOf(priority.toUpperCase());
                return ResponseEntity.ok(service.getTasksByPriority(priorityEnum));
            } catch (IllegalArgumentException e) {
                throw new BusinessException(
                        "Prioridade inválida. Valores aceitos: LOW, MEDIUM, HIGH"
                );
            }
        }

        return ResponseEntity.ok(service.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = service.getTaskById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma task foi encontrada com o id " + id));

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        Task updatedTask = service.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}