package com.example.taskmanager.repository;

import com.example.taskmanager.enums.Priority;
import com.example.taskmanager.enums.Status;
import com.example.taskmanager.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);
}