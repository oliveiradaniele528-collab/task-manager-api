package com.example.taskmanager.service;

import com.example.taskmanager.enums.Priority;
import com.example.taskmanager.enums.Status;
import com.example.taskmanager.exception.BusinessException;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final CounterService counterService;

    public TaskService(TaskRepository repository, CounterService counterService) {
        this.repository = repository;
        this.counterService = counterService;
    }

    public Task createTask(Task task) {

        validateTask(task);

        task.setId(String.valueOf(counterService.getNextSequence()));

        if (task.getStatus() == null) {
            task.setStatus(Status.PENDING);
        }

        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTasksByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return repository.findByPriority(priority);
    }

    public Optional<Task> getTaskById(String id) {
        return repository.findById(id);
    }

    public Task updateTask(String id, Task updatedTask) {

        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        if (existingTask.getStatus() == Status.COMPLETED) {
            throw new BusinessException("Tarefas concluídas não podem ser editadas");
        }

        if (updatedTask.getTitle() != null) {
            validateTitle(updatedTask.getTitle());
            existingTask.setTitle(updatedTask.getTitle());
        }

        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }

        if (updatedTask.getStatus() != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }

        if (updatedTask.getPriority() != null) {
            existingTask.setPriority(updatedTask.getPriority());
        }

        if (updatedTask.getDueDate() != null) {
            validateDueDate(updatedTask.getDueDate());
            existingTask.setDueDate(updatedTask.getDueDate());
        }

        existingTask.setUpdatedAt(LocalDateTime.now());

        return repository.save(existingTask);
    }

    public void deleteTask(String id) {
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        repository.delete(existingTask);
    }

    private void validateTask(Task task) {
        validateTitle(task.getTitle());
        validateDueDate(task.getDueDate());

        if (task.getPriority() == null) {
            throw new BusinessException("Prioridade é obrigatória");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new BusinessException("Título é obrigatório");
        }

        if (title.length() < 3 || title.length() > 100) {
            throw new BusinessException("Título deve ter entre 3 e 100 caracteres");
        }
    }

    private void validateDueDate(LocalDate dueDate) {
        if (dueDate != null && dueDate.isBefore(LocalDate.now())) {
            throw new BusinessException("Data de vencimento não pode ser no passado");
        }
    }
}